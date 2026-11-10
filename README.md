# StudentStats 📊

An Android application project for calculating student grade-group percentages and presenting the distribution in a bar chart.

## Features represented in the source

- Accepts a total student count
- Accepts counts for grade groups A, B, C, D, and F
- Checks that the sum of grade-group counts matches the total student count
- Calculates each grade group's percentage
- Includes a separate bar-chart activity for visualizing the percentages

## Tech Stack

- Java
- Android SDK (compile / target SDK 34; minimum SDK 24)
- AndroidX AppCompat
- MPAndroidChart (bar chart implementation)
- Android XML layouts and resources

## Run Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/SRIKALYANBHAVANTHI/StudentStats.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle and install the dependencies.
4. Run the **app** configuration on an Android emulator or device.

Build a debug APK:

```bash
./gradlew assembleDebug
```

Windows:

```bat
gradlew.bat assembleDebug
```

## Project Structure

- `MainActivity.java` — input validation and grade percentage calculation
- `BarChartActivity.java` — bar chart display
- `app/src/main/res/` — Android layouts and resources

## Notes

The source currently assumes a nonzero total student count. Validate inputs and test the chart flow before using the application with real data. Build and runtime behavior have not been independently verified as part of this documentation update.

---

Created by [Srikalyan Bhavanthi](https://github.com/SRIKALYANBHAVANTHI).
