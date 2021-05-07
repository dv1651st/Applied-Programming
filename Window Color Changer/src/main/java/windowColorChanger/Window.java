package windowColorChanger;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.awt.event.KeyEvent;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int width, height;
    private String title;
    private long glfwWindow;

    public float r,g,b,a;

    private static Window window = null;

    private Window() {
        this.width = 2000;
        this.height = 2000;
        this.title = "Aim";
        r = 1;
        b = 1;
        g = 1;
        a = 1;
    }

    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    public void run() {
        
        init();
        loop();

        //Free the memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        //Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }
    public void setColor(float r,float g,float b,float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;

        WriteFile file = WriteFile.get();
        file.setString("red: " + r + "green: " + g + "blue: " + b + "alpha: " + a + "\n ");
        file.run();
    }
    public void init() {
        // Setup an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        //Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        //Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED,GLFW_TRUE);

        //Create Window
        glfwWindow = glfwCreateWindow(this.width,this.height,this.title,NULL,NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        //Make OpenGL context current
        glfwMakeContextCurrent(glfwWindow);

        //Enable v-sync
        glfwSwapInterval(1);

        //Make window visible
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();


    }
    public void loop() {

        while (!glfwWindowShouldClose(glfwWindow)) {
            // Wait events
            glfwWaitEvents();

            glClearColor(r,g,b,a);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);

            if (KeyListener.isKeyPressed(GLFW_KEY_SPACE))
                setColor(.5f,.5f,.5f,1);
            if (KeyListener.isKeyPressed(KeyEvent.VK_A))
                setColor(1,0,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_B))
                setColor(0,1,0,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_C))
                setColor(0,0,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_D))
                setColor(0,0,0,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_E))
                setColor(1,.5f,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_F))
                setColor(0,1,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_G))
                setColor(1,1,0,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_H))
                setColor(1,1,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_I))
                setColor(1,1,.5f,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_J))
                setColor(.5f,0,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_K))
                setColor(.5f,1,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_L))
                setColor(.5f,.5f,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_M))
                setColor(1,.5f,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_N))
                setColor(.5f,.5f,.5f,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_O))
                setColor(1,.75f,.5f,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_P))
                setColor(.75f,.75f,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_Q))
                setColor(.5f,.567f,.5f,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_R))
                setColor(.75f,.75f,.75f,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_S))
                setColor(.85f,.23f,.13f,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_T))
                setColor(.45f,.34f,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_U))
                setColor(.56f,1,.23f,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_V))
                setColor(.66f,.45f,.11f,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_W))
                setColor(.22f,.72f,.88f,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_Y))
                setColor(.1f,.8f,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_X))
                setColor(1,.3f,1,0);
            if (KeyListener.isKeyPressed(KeyEvent.VK_Z))
                setColor(.3f,0,1,0);
        }
    }
}
