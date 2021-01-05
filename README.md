# Appium Example

Project based on [Appium - Mobile Automation](https://www.udemy.com/course/mobile-automation-using-appiumselenium-3/) course by Rahul Shetty for Appium knowledge

# Prerequisites

Requires an Android emulator in order to run the tests

# Usage

```
mvn test
```
This will run the test app on the default emulator called "Emulator".

If your emulator does not have this name, you can simply use

```
mvn test -DdeviceType=<your_emulator_name>
```

You can also edit your device on the global.properties file

```
device = Emulator
emulatorId = emulator-5554
```
