MODEL
- In the model, we implemented the interfaces for the layer and project and chose appropriate
  data representation to be used throughout the code.

VIEW
- In the view we created a visual representation of a model of collaging and created a view Object
  for a command line based collaging project.

CONTROLLER
- The controller is where we implemented all of our methods.
- These methods include loading the image, selecting the current commands, creating a new project,
  returning a project, loading a project, saving a project, saving a layer as a ppm file, adding
  a layer, placing an image on a layer, setting a filter, saving an image, quitting a project,
  and rendering the layer/filter type commands.

TESTS
- In the test file, we tested all our methods and various cases of it to ensure it is running.
  We also tests each of the classes we made in the controller (layer, pixel, and project)

SCRIPT OF COMMANDS
1. to create a new project type --> new-project 800 600
2. to add a new layer type --> add-layer layerName
3. to add an image to a layer type --> add-image-to-layer layerName images/tako.ppm
4. to set a certain filter type with red-component --> set-filter layerName red-component
5. to save an image type --> save-image image.ppm
6. to save a project type --> save-project
7. to load a project type --> load-project image.Project
8. to quit type --> quit
