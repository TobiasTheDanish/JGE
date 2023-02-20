package Rendering;

import Math.Matrix.Matrix4;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import Math.Vector.Vector4D;
import Utils.Color.Color;
import Utils.ShaderUtils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader extends Component {
    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;

    private final int rendererID;
    private final Map<String, Integer> locationCache = new HashMap<>();

    public Shader(String vertexPath, String fragmentPath) {
        rendererID = ShaderUtils.parseShader(vertexPath, fragmentPath);
    }

    @Override
    public void bind() {
        glUseProgram(rendererID);
        int error = glGetError();
        if (error != GL_NO_ERROR) {
            System.out.println(rendererID + ", " + error);
            System.out.println(glGetProgramInfoLog(rendererID));
        }
    }

    @Override
    public void unbind() {
        glUseProgram(0);
    }

    public void setUniform(String uniform, int value) {
        int currentProgram = glGetInteger(GL_CURRENT_PROGRAM);
        if (currentProgram != rendererID) {
            bind();
        }
        glUniform1i(getUniformLocation(uniform), value);
    }

    public void setUniform(String uniform, float value) {
        int currentProgram = glGetInteger(GL_CURRENT_PROGRAM);
        if (currentProgram != rendererID) {
            bind();
        }
        glUniform1f(getUniformLocation(uniform), value);
    }

    public void setUniform(String uniform, Vector2D vec2) {
        int currentProgram = glGetInteger(GL_CURRENT_PROGRAM);
        if (currentProgram != rendererID) {
            bind();
        }
        glUniform2f(getUniformLocation(uniform), vec2.x, vec2.y);
    }

    public void setUniform(String uniform, Vector3D vec3) {
        int currentProgram = glGetInteger(GL_CURRENT_PROGRAM);
        if (currentProgram != rendererID) {
            bind();
        }
        glUniform3f(getUniformLocation(uniform), vec3.x, vec3.y, vec3.z);
    }

    public void setUniform(String uniform, Color color) {
        int currentProgram = glGetInteger(GL_CURRENT_PROGRAM);
        if (currentProgram != rendererID) {
            bind();
        }
        glUniform4f(getUniformLocation(uniform), color.r, color.g, color.b, color.a);
    }

    public void setUniform(String uniform, Matrix4 matrix) {
        int currentProgram = glGetInteger(GL_CURRENT_PROGRAM);
        if (currentProgram != rendererID) {
            bind();
        }
        glUniformMatrix4fv(getUniformLocation(uniform), false, matrix.toFloatBuffer());
    }

    private int getUniformLocation(String name) {
        if (locationCache.containsKey(name)) {
            return locationCache.get(name);
        }

        int location = glGetUniformLocation(this.rendererID, name);
        if (location == -1) {
            System.out.println("[WARNING] Uniform " + name + " was not found.");
        } else {
            locationCache.put(name, location);
        }

        return location;
    }
}
