package at.net.fynn.com.imgui;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiWindowFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.type.ImString;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;




/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */

public class Main extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture image;

    private MyImgui myImgui;

    @Override
    //general create
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        myImgui = new MyImgui();
    }

    @Override
    //general render
    public void render() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
        myImgui.render();
    }

    @Override
    //general dispose
    public void dispose() {
        batch.dispose();
        image.dispose();
        myImgui.dispose();
    }


    public static String newGameLog(String logTag, String logMessage) {
        //White Log message / normal log message
        ImGui.begin("Console");

        ImGui.text("[" + logTag + "] " + logMessage);

        ImGui.end();
        return "[" + logTag + "] " + logMessage;
    }

    public static String newGameLogError(String logTag, String logMessage) {
        //Red Log message
        ImGui.begin("Console");

        ImGui.textColored(227, 43, 43,255, "[" + logTag + "/ERROR] " + logMessage);

        ImGui.end();
        return "[" + logTag + "/ERROR] " + logMessage;
    }

    public static String newGameLogWarning(String logTag, String logMessage) {
        //Yellow Log message


        ImGui.textColored(199, 160, 18,255, "[" + logTag + "/WARNING] " + logMessage);

   
        return "[" + logTag + "/WARNING] " + logMessage;
    }
        



    static class MyImgui {

        ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
        ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

        public MyImgui() {
            create();
            
        }


        //ImGui create
        public void create() {
            long windowHandle = ((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle();
            GLFW.glfwMakeContextCurrent(windowHandle);
            GL.createCapabilities();
            ImGui.createContext();
            ImGuiIO io = ImGui.getIO();
            io.setIniFilename(null);
            io.getFonts().addFontDefault();
            io.getFonts().build();

            imGuiGlfw.init(windowHandle, true);
            imGuiGl3.init("#version 110");

        }

        //ImGui render
        public void render() {


            imGuiGlfw.newFrame();
            ImGui.newFrame();

            ImGui.styleColorsDark();

            
            
            
            

            // --- ImGUI ---
            //ImGui.setNextWindowPos(0, 650);
            //ImGui.setNextWindowSize(650, 150);


            ImGui.begin("Console");

            
            newGameLog("CONSOLE/DEBUG", "REGISTER CONSOLE");
            ImGui.text("---------------------------");
            newGameLogError("CONSOLE/DEBUG", "DEBUG ERROR");
            newGameLogWarning("CONSOLE/DEBUG", "DEBUG WARNING");
            ImGui.end();


            //Main Menu Bar
            if (ImGui.beginMainMenuBar()) {
                if (ImGui.beginMenu("Options", true)) {
                    if (ImGui.menuItem("Exit", "CTRL+Q")) {
                        Gdx.app.exit();
                    }
                    if (ImGui.menuItem("restart", "CTRL+R")) {
                        Gdx.app.log("restart", "No restart method found");
                    }
                    ImGui.endMenu();
                }
                if (ImGui.beginMenu("View")) {
                    if (ImGui.menuItem("Debug")) {
                        ImGui.begin("Console");
                        newGameLogWarning("CONSOLE/DEBUG", "DEBUG WARNING from menu bar");
                        ImGui.end();
                    }
                    if (ImGui.menuItem("Collapse all")) {
                        ImGui.openPopup("Debug");
                        ImGui.setWindowCollapsed("Console", true);
                    }
                    ImGui.endMenu();
                }
                ImGui.endMainMenuBar();
            }

            //Info (right)
            ImGui.setNextWindowPos(650, 19);
            ImGui.setNextWindowSize(650, 800);

            
            ImGui.begin("INFO", ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse);

            ImGui.beginTabBar("tabs");
            if (ImGui.beginTabItem("PROPERTIES")) {
                

                ImGui.text("temp propertys tab 1");
                ImGui.button("Button");
                ImGui.text("[string mit text]Lorem Ipsum und so weiter und so fort. \nUnd mit (backslash n) kommt ne neue Zeile");

                ImGui.endTabItem();
            }
            if (ImGui.beginTabItem("tab 2")) {

                ImGui.text("Tab 2");

                if (ImGui.button("Crashes the program, not joking")) {
                    
                    throw new RuntimeException("as i said, it crashes");
                }

                if (ImGui.button("Get Application Logger")) {
                    
                    Gdx.app.log("APPLOGGER", "get app logger, tbd");
                    
                }

                ImGui.endTabItem();
            }
            ImGui.endTabBar();
            ImGui.end();
            
            ImGui.begin("Console");
            newGameLog("DEBUG", "imgui new window");
            ImGui.end();

            //ImGui.showDemoWindow();
            
            



            //Menu (bottom)
            ImGui.setNextWindowPos(0, 500);
            ImGui.setNextWindowSize(650, 150);
            ImGui.begin("MENU", ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse);
            ImGui.beginGroup();
            ImGui.text("BUTTONGROUP");
            if (ImGui.isItemHovered()) {
                ImGui.setTooltip("BUTTONGROUP");
            }
            
            if (ImGui.button("BUTTON_1")) {
                ImGui.openPopup("button_1_popup");

            }
            ImGui.endGroup();
            ImGui.end();
            
            // ---

            ImGui.render();
            imGuiGl3.renderDrawData(ImGui.getDrawData());
        }

        //ImGui dispose
        public void dispose() {
            imGuiGl3.dispose();
            imGuiGlfw.dispose();
            ImGui.destroyContext();
        }
    }
}
