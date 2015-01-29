package framework.util.dataTypes;

/**
 * @author William Gervasio
 *         Helps create a list of primitive floats this is of variable length
 */
public class FloatArrayList {

	private static final int DEFAULT_INITIAL_CAPACITY = 10;
	private static final float DEFAULT_GROWTH_FACTOR = 2.0f;

	private float [] data;
	private int size;
	private final float growth;

	public FloatArrayList () {
		this ( DEFAULT_INITIAL_CAPACITY, DEFAULT_GROWTH_FACTOR );
	}

	public FloatArrayList ( final int capacity ) {
		this ( capacity, DEFAULT_GROWTH_FACTOR );
	}

	public FloatArrayList ( final int capacity, final float growthFactor ) {
		data = new float [ capacity ];
		size = 0;
		growth = growthFactor;
	}

	public int size () {
		return size;
	}

	public void add ( final float value ) {
		if ( size == data.length - 1 ) {
			final float [] newDataArray = new float [ ( int ) ( data.length * growth ) ];
			System.arraycopy ( data, 0, newDataArray, 0, data.length );
			data = newDataArray;
		}
		data[ size ] = value;
		++size;
	}

	public void remove ( final int position ) {
		final int indexesUntilTheEnd = position - size;
		if ( position >= 0 && indexesUntilTheEnd > 0 ) {
			System.arraycopy ( data, position + 1, data, position, indexesUntilTheEnd );
			--size;
		}
	}

	public void clear () {
		size = 0;
	}

	public float [] toArray () {
		final float [] minimumArray = new float [ size ];
		System.arraycopy ( data, 0, minimumArray, 0, size );
		return minimumArray;
	}

}
