# AndroidMaterialPreference - RELEASE NOTES

## Version 2.0.4 (Feb. 25th 2016)

A minor release, which introduces the following changes:

- The library is from now on distributed under the Apache License version 2.0. 
- Updated dependency "AndroidUtil" to version 1.4.3.
- Updated dependency "AndroidMaterialDialog" to version 3.1.1.
- Updated dependency "AndroidMaterialValidation" to version 2.0.1.
- Updated AppCompat support library to version 23.2.0.
- Minor changes of the example app.

## Version 2.0.3 (Feb. 13th 2016)

A minor release, which introduces the following changes:

- Version 3.1.0 of the library "AndroidMaterialDialog" is now used.

## Version 2.0.2 (Dec. 13th 2015)

A minor release, which introduces the following changes:

- Implemented enhancement https://github.com/michael-rapp/AndroidMaterialPreferences/issues/6 by updating to version 3.0.1 of the library "AndroidMaterialDialog".

## Version 2.0.1 (Nov. 12th 2015)

A minor release, which introduces the following changes:

- Version 3.0.0 of the library "AndroidMaterialDialog" is now used.

## Version 2.0.0 (Oct. 19th 2015)

A major release, which introduces the following changes:

- The project has been migrated from the legacy Eclipse ADT folder structure to Android Studio. It now uses the Gradle build system and the library as well as the example app are contained by one single project.
- The library can now be added to Android apps using the Gradle dependency `com.github.michael-rapp:android-material-preferences:2.0.0`

## Version 1.7.1 (Sept. 24th 2015)

A minor release, which introduces the following changes:

- The number of columns and preview size, which is used to show colors in the dialog of a `ColorPalettePreference` has been adapted on tablets.

## Version 1.7.0 (Sept. 16th 2015)

A feature release, which introduces the following new features:

- Added a preference, which allows to select multiple values from a list (`MultiChoiceListPreference`).
- Previews of the `ColorPalettePreference` are now created asynchronously.
- Round previews of the `ColorPalettePreference` are now rendered antialiased when using no border.

# Version 1.6.0 (Sept. 13th 2015)

A feature release, which introduces the following changes:

- The library does now rely on version 1.2.0 of the library "AndroidMaterialDialog".
- Additional setter/getter methods and XML attributes have been added in order to support greater customization of preferences' dialogs. All dialog preferences do now allow to specify the message color and background of their dialogs and the `ListPreference` furthermore allows to specify the color of the list items and controls.

## Version 1.5.0 (Sept. 11th 2015)

A feature release, which introduces the following new features:

- The preferences `NumberPickerPreference` and `DigitPickerPreference` do now allow to set an unit, which is used for printing textual representations of the preferences' current numbers.
- The preference `NumberPickerPreference` does now support to set the step size, the preference's number should be increased or decreased by when moving its selector wheel.
- Some attributes have been renamed for greater consistency between the library's preferences.
- Fixed issue https://github.com/michael-rapp/AndroidMaterialPreferences/issues/3

## Version 1.4.0 (Aug. 31th 2015)

A feature release, which introduces the following new features:

- Added a preference, which allows to choose a color from a predefined color palette (`ResolutionPreference`).
- Added a preference, which provides a toggleable option using a `Switch` widget (`SwitchPreference`).
- Minor implementation and API changes for greater consistency with the Android SDK's built-in preferences.

## Version 1.3.0 (Aug. 22th 2015)

A feature release, which introduces the following new features:

- Added a preference, which allows to enter two-dimensional image or video resolutions using two `EditText` widgets (`ResolutionPreference`).

## Version 1.2.0 (June 8th 2015)

A feature release, which introduces the following new features:

- An `EditTextPreference` can now be validated by using the library "AndroidMaterialValidation".

## Version 1.1.1 (Apr. 3rd 2015)

A bugfix release, which fixes the following issues:

- https://github.com/michael-rapp/AndroidMaterialPreferences/issues/1

## Version 1.1.0 (Apr. 3rd 2015)

A feature release, which introduces the following new features:

- Validators can now be added to an `EditTextPreference` in order to validate the entered text and show error messages if needed.
- Added a preference, which allows to choose a decimal number via a `NumberPicker` widget (`NumberPickerPreference`).
- Added a preference, which allows to choose a decimal number using multiple `NumberPicker` widgets, which allow to choose each digit individually (`DigitPickerPreference`).

## Version 1.0.0 (Nov. 19th 2014)

The first stable release, which initially provides the following preferences, which are designed according to Android 5's Material Design guidelines even on pre-Lollipop devices:
	
- A preference, which allows to enter a text via an `EditText` widget (`EditTextPreference`).
- A preference, which allows to select a value from a list (`ListPreference`).
- A preference, which allows to choose a floating point value or an integer value from a continuous range via a `SeekBar` widget (`SeekBarPreference`).