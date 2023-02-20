package Utils;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class ShaderUtils {
    public static int parseShader(String vertexPath, String fragmentPath) {
        String vertexSource = FileUtils.readFile(vertexPath);
        String fragmentSource = FileUtils.readFile(fragmentPath);

        return createShader(vertexSource, fragmentSource);
    }

    public static int createShader(String vertexSource, String fragmentSource) {
        int program = glCreateProgram();

        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexSource);
        glCompileShader(vertexShader);
        if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println(glGetShaderInfoLog(vertexShader));
        }

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentSource);
        glCompileShader(fragmentShader);
        if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println(glGetShaderInfoLog(fragmentShader));
        }

        glAttachShader(program, vertexShader);
        glAttachShader(program, fragmentShader);

        glLinkProgram(program);
        if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
            System.out.println(glGetProgramInfoLog(program));
        }

        glValidateProgram(program);
        if (glGetProgrami(program, GL_VALIDATE_STATUS) == GL_FALSE) {
            System.out.println(glGetProgramInfoLog(program));
        }

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);

        return program;
    }
}
