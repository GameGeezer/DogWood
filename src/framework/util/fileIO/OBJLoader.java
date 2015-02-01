package framework.util.fileIO;

import framework.graphics.Mesh;
import framework.util.MeshBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OBJLoader {

	private static final String COMMENT_LINE_PREFIX = "#";
	private static final String OBJECT_NAME_PREFIX = "o";
	private static final String SHADING_PREFIX = "s";
	private static final String MATERIAL_USE_PREFIX = "usemtl";
	private static final String VERTEX_POSITION_PREFIX = "v";
	private static final String VERTEX_TEXTURE_PREFIX = "vt";
	private static final String VERTEX_NORMAL_PREFIX = "vn";
	private static final String FACE_PREFIX = "f";

	private static final String POSITION_COMPONENT = "POSITION";
	private static final String TEXCOORD_COMPONENT = "TEXCOORD";
	private static final String NORMAL_COMPONENT = "NORMAL";

	private static final int POSITION_SIZE = 3;
	private static final int TEXCOORD_SIZE = 2;
	private static final int NORMAL_SIZE = 3;

	private static class OBJParseException extends ParseException {

		private final String file;
		private final String remaining;

		public OBJParseException ( final String s, final int errorOffset ) {
			this ( s, null, errorOffset, null );
		}

		public OBJParseException ( final String s, final String file, final int errorOffset ) {
			this ( s, file, errorOffset, null );
		}

		public OBJParseException ( final String s, final int errorOffset, final String remaining ) {
			this ( s, null, errorOffset, remaining );
		}

		public OBJParseException ( final String s, final String file, final int errorOffset, final String remaining ) {
			super ( s, errorOffset );
			this.file = file;
			this.remaining = remaining;
		}

		@Override
		public String toString() {
			final StringBuilder sbuilder = new StringBuilder ();
			sbuilder.append ( super.toString () );
			if ( file != null ) {
				sbuilder.append ( " fileName: " );
				sbuilder.append ( file );
			}
			sbuilder.append ( " line " );
			sbuilder.append ( this.getErrorOffset () );
			if ( remaining != null ) {
				sbuilder.append ( ", remaining: " );
				sbuilder.append ( remaining );
			}
			return sbuilder.toString ();
		}

	}

	private static enum FaceModel { VERTEX, VERTEX_UV, VERTEX_UV_NORMAL, VERTEX_NORMAL }

	public static final OBJLoader LOADER = new OBJLoader ();

	private final List < float [] > vertices;
	private final List < float [] > textures;
	private final List < float [] > normals;

	private final MeshBuilder builder;

	// Lazily initialized fields
	private String fileName;
	private FaceModel model;
	private int lineNo;

	public OBJLoader () {

		vertices = new ArrayList < float [] > ();
		textures = new ArrayList < float [] > ();
		normals = new ArrayList < float [] > ();

		builder = new MeshBuilder ();
		builder.createComponent ( POSITION_COMPONENT, POSITION_SIZE );
		builder.createComponent ( TEXCOORD_COMPONENT, TEXCOORD_SIZE );
		builder.createComponent ( NORMAL_COMPONENT, NORMAL_SIZE );

	}

	public Mesh loadModel ( final String filePath ) throws ParseException, FileNotFoundException {

		final File file = new File ( filePath );
		if ( !file.exists () ) {
			throw new FileNotFoundException ( "File " + filePath + " does not exist!" );
		}

		model = null;
		fileName = filePath;
		lineNo = 0;

		final StringBuilder sourceBuilder = new StringBuilder ();

		try ( final Scanner pre = new Scanner ( file ) ) {
			while ( pre.hasNextLine () ) {
				final String line = pre.nextLine ().trim ();
				final String processedLine = line.replaceAll ( "/", " / " );
				sourceBuilder.append ( processedLine );
				sourceBuilder.append ( "\n" );
			}
		} catch ( final Exception e ) {
			throw new OBJParseException ( "Unknown parsing error. No further information.", fileName, -1 );
		}

		final Scanner scanner = new Scanner ( sourceBuilder.toString () );

		while ( scanner.hasNextLine () ) {

			if ( scanner.hasNext ( COMMENT_LINE_PREFIX ) ) {
				// Comment line - skip it
			} else if ( scanner.hasNext ( OBJECT_NAME_PREFIX ) ) {
				// Object name - skip it
			} else if ( scanner.hasNext ( MATERIAL_USE_PREFIX ) ) {
				// Material - skip it
			} else if ( scanner.hasNext ( SHADING_PREFIX ) ) {
				// Shading - skip it
			} else if ( scanner.hasNext ( VERTEX_TEXTURE_PREFIX ) ) {
				// Vertex Texture UV
				parseVT ( scanner );
			} else if ( scanner.hasNext ( VERTEX_NORMAL_PREFIX ) ) {
				// Vertex Normal
				parseVN ( scanner );
			} else if ( scanner.hasNext ( VERTEX_POSITION_PREFIX ) ) {
				// Vertex Position
				parseV ( scanner /* , vertices */ );
			} else if ( scanner.hasNext ( FACE_PREFIX ) ) {
				// Face
				parseF ( scanner );
			} else {
				// Unexpected ( at this time )
				System.err.println ( scanner.nextLine () );
				throw new OBJParseException ( "Unexpected token", fileName, lineNo, scanner.nextLine () );
			}

			scanner.nextLine ();
			++lineNo;

		}

		vertices.clear ();
		textures.clear ();
		normals.clear ();

		return builder.build ();

	}

	private void parseV ( final Scanner scanner ) throws ParseException {

		scanner.next ( VERTEX_POSITION_PREFIX );

		if ( !scanner.hasNextFloat () ) throw new OBJParseException ( "Expected float component 'x'", fileName, lineNo, scanner.nextLine () );
		final float x = scanner.nextFloat ();

		if ( !scanner.hasNextFloat () ) throw new OBJParseException ( "Expected float component 'y'", fileName, lineNo, scanner.nextLine () );
		final float y = scanner.nextFloat ();

		if ( !scanner.hasNextFloat () ) throw new OBJParseException ( "Expected float component 'z'", fileName, lineNo, scanner.nextLine () );
		final float z = scanner.nextFloat ();

		final float w = scanner.hasNextFloat () ? scanner.nextFloat () : 1.0f;

		vertices.add ( new float [] { x, y, z, w } );

	}

	private void parseVT ( final Scanner scanner ) throws ParseException {

		scanner.next ( VERTEX_TEXTURE_PREFIX );

		if ( ! scanner.hasNextFloat () ) throw new OBJParseException ( "Expected float component 'u'", fileName, lineNo, scanner.nextLine () );
		final float u = scanner.nextFloat ();
		if ( u < 0.0f) throw new OBJParseException ( "Float component 'u' is outside bounds! " + u + " < 0.0f", fileName, lineNo );
		if ( u > 1.0f ) throw new OBJParseException ( "Float component 'u' is outside bounds! " + u + " > 1.0f", fileName, lineNo );

		if ( ! scanner.hasNextFloat () ) throw new OBJParseException ( "Expected float component 'v'", fileName, lineNo, scanner.nextLine () );
		final float v = scanner.nextFloat ();
		if ( v < 0.0f ) throw new OBJParseException ( "Float component 'v' is outside bounds! " + v + " < 0.0f", fileName, lineNo );
		if ( v > 1.0f ) throw new OBJParseException ( "Float component 'v' is outside bounds! " + u + " > 1.0f", fileName, lineNo );

		// W ( Weight ) component is optional
		final float w;
		if ( scanner.hasNextFloat () ) {
			w = scanner.nextFloat ();
			if ( w < 0.0f ) throw new OBJParseException ( "Float component 'w' is outside bounds! " + w + " < 0.0f", fileName, lineNo );
			if ( w > 1.0f ) throw new OBJParseException ( "Float component 'w' is outside bounds! " + w + " > 1.0f", fileName, lineNo );
		} else {
			w = 0.0f;
		}

		textures.add ( new float [] { u, v, w } );

	}

	private void parseVN ( final Scanner scanner ) throws ParseException {

		scanner.next ( VERTEX_NORMAL_PREFIX );

		if ( ! scanner.hasNextFloat () ) throw new OBJParseException ( "Expected float component 'x'", fileName, lineNo, scanner.nextLine () );
		final float x = scanner.nextFloat ();

		if ( ! scanner.hasNextFloat () ) throw new OBJParseException ( "Expected float component 'y'", fileName, lineNo, scanner.nextLine () );
		final float y = scanner.nextFloat ();

		if ( ! scanner.hasNextFloat () ) throw new OBJParseException ( "Expected float component 'z'", fileName, lineNo, scanner.nextLine () );
		final float z = scanner.nextFloat ();

		normals.add( new float [] { x, y, z } );

	}

	private void parseF ( final Scanner scanner ) throws ParseException {

		scanner.next ( FACE_PREFIX );

		for ( int i = 0; i < 3; ++i ) {

			parseFI ( scanner );

		}

	}

	private void parseFI ( final Scanner scanner ) throws ParseException {

		if ( ! scanner.hasNextInt () ) throw new OBJParseException ( "Expected int component 'position index'", fileName, lineNo, scanner.nextLine () );
		final int positionIndex = scanner.nextInt ();
		if ( positionIndex < 1 ) throw new OBJParseException ( "Int component 'position index' is invalid! " + positionIndex + " < 1 ", fileName, lineNo );

		final FaceModel currentModel;

		final float [] pRef = vertices.get ( positionIndex - 1 );
		builder.addToComponent ( POSITION_COMPONENT, pRef );

		if ( scanner.hasNext ( "/" ) ) {

			scanner.next ( "/" );

			if ( scanner.hasNextInt () ) {

				final int texCoordIndex = scanner.nextInt ();
				if ( texCoordIndex < 1 ) throw new OBJParseException ( "Int component 'normal index' is invalid! " + texCoordIndex + " < 1 ", fileName, lineNo );

				final float [] uvRef = textures.get ( texCoordIndex - 1 );
				builder.addToComponent ( TEXCOORD_COMPONENT, uvRef );

				if ( scanner.hasNext ( "/" ) ) {

					scanner.next ( "/" );

					if ( ! scanner.hasNextInt () ) throw new OBJParseException ( "Expected int component 'normal index'", fileName, lineNo, scanner.nextLine () );
					final int normalIndex = scanner.nextInt ();
					if ( normalIndex < 1 ) throw new OBJParseException ( "Int component 'normal index' is invalid! " + normalIndex + " < 1 ", fileName, lineNo );

					final float [] nRef = normals.get ( normalIndex - 1 );
					builder.addToComponent ( NORMAL_COMPONENT, nRef );

					if ( model == null ) {

						model = FaceModel.VERTEX_UV_NORMAL;
						return;

					}

					currentModel = FaceModel.VERTEX_UV_NORMAL;

				} else {

					if ( model == null ) {

						model = FaceModel.VERTEX_UV;
						return;

					}

					currentModel = FaceModel.VERTEX_UV;

				}

			} else if ( scanner.hasNext ( "/" ) ) {

				scanner.next ( "/" );

				if ( ! scanner.hasNextInt () ) throw new OBJParseException ( "Expected int component 'normal index'", fileName, lineNo, scanner.nextLine () );
				final int normalIndex = scanner.nextInt ();
				if ( normalIndex < 1 ) throw new OBJParseException ( "Int component 'normal index' is invalid! " + normalIndex + " < 1 ", fileName, lineNo );

				final float [] nRef = normals.get ( normalIndex - 1 );
				builder.addToComponent ( NORMAL_COMPONENT, nRef );

				if ( model == null ) {

					model = FaceModel.VERTEX_NORMAL;
					return;

				}

				currentModel = FaceModel.VERTEX_NORMAL;

			} else {

				throw new OBJParseException ( "Expected int component 'uv index' or 'normal index'", fileName, lineNo, scanner.nextLine () );

			}

		} else {

			if ( model == null ) {

				model = FaceModel.VERTEX;
				return;

			}

			currentModel = FaceModel.VERTEX;

		}

		if ( model != currentModel ) {

			throw new OBJParseException ( "Unexpected and inconsistent change in face format ( old: " + model + ", new: " + currentModel + " )", fileName, lineNo );

		}

		builder.addIndex ( positionIndex );

	}

}
