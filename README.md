# Stress Meter App

## Introduction

The Stress Meter app is an innovative mobile application designed to help individuals track and manage their stress levels in real-time. Inspired by the psychological Perceived Stress Scale and the work on PAM (Photographic Affect Meter), this app merges mobile computing with psychology to offer users a unique way to understand their stress patterns. Whether you're a college student navigating the complexities of campus life or anyone interested in monitoring their stress, the Stress Meter app provides a personalized and interactive experience.

## Features

- **Interactive Stress Assessment**: Utilize a curated set of images that correlate with different levels of perceived stress. Select how you're feeling based on these images to log your current stress level.
- **Historical Data Visualization**: All entries are recorded with timestamps in a `stress_timestamp.csv` file, allowing for the visualization of stress levels over time. This feature helps users identify patterns and triggers in their stress levels.
- **Customizable UI**: Experience the app with a modern and customizable user interface that includes an AppBar and NavigationView for easy navigation between different views.
- **Sound and Vibration Feedback**: The app engages users with sound and vibration feedback upon starting, enhancing the interactive experience.
- **Multiple Views for Enhanced Interaction**: The app comprises several views - the image request grid view, image response activity, visualization of stress data, and a NavigationView for seamless switching between functionalities.

## Getting Started

1. **Installation**: Begin by installing the StressMeter APK on your device to familiarize yourself with its workflow and features.
2. **Explore the UI**: The app introduces a new UI incorporating the AppBar and NavigationView, offering a fresh look and enhanced usability.
3. **Data Visualization**: Experiment with the visualization features by reading data from the `stress_timestamp.csv` file and using graph plotting libraries to analyze your stress patterns.

## Implementation Highlights

- **GridView for Image Selection**: Users can select their stress level through a GridView of images. This selection process is intuitive and visually driven, making it easier to log stress levels.
- **Navigation Drawer Activity**: The app uses a Navigation Drawer Activity for easy navigation, adhering to modern app design standards.
- **LiveData and MVVM Architecture**: The app is designed following the MVVM architecture, ensuring a robust, maintainable, and testable codebase.

## Customization and Design

Feel free to design and customize the app as you see fit, provided the basic functionality is implemented. The app's design encourages deep thinking about systems design and offers a substantial coding challenge, pushing you to learn and apply concepts not covered in class.

## Conclusion

The Stress Meter app is more than just a tool; it's your personal assistant for stress management. By providing real-time feedback and historical data visualization, the app empowers users to recognize and address their stress triggers. Embrace this opportunity to merge technology with psychology and embark on a journey towards better stress management.

Good luck with your implementation, and remember, this project is a unique blend of mobile computing and psychology designed to enhance your understanding and management of stress.
