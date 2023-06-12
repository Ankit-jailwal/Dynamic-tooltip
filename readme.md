# Plotline Dynamic Tooltip

![App Icon](https://uploads-ssl.webflow.com/62bc395da3c33ed00dcc1317/6450f144e77b77f1598b596f_Plotline%20logo.svg)

Plotline Dynamic Tooltip is a mobile application that provides in-app nudges in the form of tooltips to guide users through various features. Tooltips are rendered with dynamic positioning, style, and text on the target element.

## Design

The design for the mobile screen and tooltip component can be found in this [Figma file](https://www.figma.com/file/TUzRJKgNhcYiL86rUx0Ojx/Plotline-Mobile-Assignment?type=design&node-id=0-1&t=5POQcvrnxoUHKH8H-0).

## Screenshots
### 1. Customize tooltip properties
  <img src="https://github.com/Ankit-jailwal/PlotLine-assignment/assets/55527244/da30b4cc-2862-4927-ae8d-ff5430c8b597" alt="da30b4cc-2862-4927-ae8d-ff5430c8b597" width="220"/>
  
### 2. Tooltip testing
  <img src="https://github.com/Ankit-jailwal/PlotLine-assignment/assets/55527244/f469951e-f5a1-4463-a07b-17378710a703" alt="f469951e-f5a1-4463-a07b-17378710a703" width="190"/>
  <img src="https://github.com/Ankit-jailwal/PlotLine-assignment/assets/55527244/dcb41982-98f6-40e3-86d9-2e963b06db7f" alt="dcb41982-98f6-40e3-86d9-2e963b06db7f" width="190"/>
  <img src="https://github.com/Ankit-jailwal/PlotLine-assignment/assets/55527244/82e05fc3-efa2-4e70-a88e-c1d6093fc60b" alt="82e05fc3-efa2-4e70-a88e-c1d6093fc60b" width="190"/>
  <img src="https://github.com/Ankit-jailwal/PlotLine-assignment/assets/55527244/8a081f4b-0753-4e27-b019-2228fd8cf30b" alt="8a081f4b-0753-4e27-b019-2228fd8cf30b" width="190"/>
  <img src="https://github.com/Ankit-jailwal/PlotLine-assignment/assets/55527244/f4507b1d-2ce5-472d-9b92-22b797b2afab" alt="f4507b1d-2ce5-472d-9b92-22b797b2afab" width="190"/>


## Features

1. **Tooltip Configuration**
   - Ability to set the target element, style, and text of the tooltip.
   - Options to include an image inside the tooltip.
   - Styling inputs provided on the configuration screen for customization.

2. **Mobile Screen Preview**
   - A preview of a mobile screen with button elements.
   - Buttons act as the target elements for rendering the tooltips.

3. **Dynamic Tooltip Rendering**
   - The tooltip component is built and positioned without using any additional library.
   - Seamless functionality for buttons at any location on the mobile screen.

4. **Tooltip Image Support**
   - The ability to include images inside the tooltips.
   - Supports various image formats such as PNG and JPEG.
   - Images can be fetched from URL.

## Implementation Details

The mobile application is built using the following technologies and libraries:

- Data Binding
- Fragment
- Glide
- Binding Adapter
- View Binding
- Room
- Coroutines
- ViewModel
- LiveData

## Setup and Configuration

1. Clone the project repository from [repository link](https://github.com/Ankit-jailwal/PlotLine-assignment).
2. Open the project in Android Studio.
3. Ensure the required dependencies are installed and up to date.
4. Build and run the project on an Android emulator or physical device.

## Download APK

You can download the latest APK of the application from this [Google Drive link](https://drive.google.com/file/d/1nDlAFgowgQ9K_7w7Z6q1Lnuvjm-gCqyv/view?usp=sharing). The APK size is approximately 7.3 MB.

## Usage

1. Launch the mobile application.
2. On the main screen, explore the mobile screen preview with various buttons.
3. Tap on the "Render Tooltip" button to access the tooltip  editor screen.
4. Set the target element, position, style, and text for the tooltip.
5. Optionally, include an image inside the tooltip.
6. Save the configuration.
7. Return to the mobile screen preview and interact with the target element to trigger the tooltip.
8. The tooltip will be rendered based on the configured parameters.

---

This project implements dynamic tooltips in a mobile app, utilizing Room Database for data storage. If you have any feedback or suggestions, please don't hesitate to let me know.

Happy coding!

[repository-link]: https://github.com/Ankit-jailwal/PlotLine-assignment


