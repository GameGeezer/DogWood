import framework.graphics.opengl.ShaderProgram
import framework.util.fileIO.FileUtil

String vertexShaderName
String fragmentShaderName
Map<Integer, String> attributes

String vertexShaderPath = "res/" + vertexShaderName
String fragmentShaderPath = "res/" + fragmentShaderName

String vertexShaderText = FileUtil.readText(vertexShaderPath)
String fragmentShaderText = FileUtil.readText(fragmentShaderPath)

return new ShaderProgram(vertexShaderText, fragmentShaderText, attributes)