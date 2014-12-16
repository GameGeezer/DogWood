import framework.graphics.opengl.ShaderProgram
import framework.util.fileIO.FileUtil

public String vertexShaderName
public String fragmentShaderName
public Map<Integer, String> attributes

private String vertexShaderPath = "res/shaders/" + vertexShaderName
private String fragmentShaderPath = "res/shaders/" + fragmentShaderName

private String vertexShaderText = FileUtil.readText(vertexShaderPath)
private String fragmentShaderText = FileUtil.readText(fragmentShaderPath)

return new ShaderProgram(vertexShaderText, fragmentShaderText, attributes)