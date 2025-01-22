# ImGui + LibGDX Informatik Project
[SpaiR/imgui-java](https://github.com/SpaiR/imgui-java) und [libgdx](https://github.com/libgdx/libgdx).

The [the official libgdx wiki](https://libgdx.com/wiki/graphics/2d/imgui) Imgui Java impl für gdx, is aber nicht so gut

[SpaiR/imgui-java](https://github.com/SpaiR/imgui-java) war die einzige die ich funktionierend habe xD

## Versions Used

* **LibGDX** 1.11.0
* **imgui-java** 1.86.8
* **gradle** 7.6

Base project mit [gdx-liftoff](https://github.com/tommyettinger/gdx-liftoff) generiert.

## Relevant Files

* **gradle.properties** - Defines the imgui version used
* **build.gradle** - Defines the imgui dependencies for the core and lwjgl3 subproject
* **core/build.gradle** - Defines the lwjgl3 dependency needed in core (unfortunately)
* **core/src/main/java/at/example/Main** - Shows how to setup ImGui (contained in the **MyImgui** inner class)
