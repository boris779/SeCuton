package at.co.boris.kcss.driverutil

import org.openqa.selenium.Dimension

enum class DriverType {
    CHROME,
    FIREFOX,
    OPERA,
    EDGE,
    IE,
    REMOTE_CHROME,
    REMOTE_FIREFOX,
    REMOTE_ANDROID,
    REMOTE_OPERA,
    APPIUM_ANDROID_DEVICE,
    REMOTE_CHROME_MOBILE,
    REMOTE_CHROME_MOBILE_EMULATION,
    CHROME_MOBILE_EMULATION
}

enum class ScreenResolutions (val resolution : String, val  width: Int, val height: Int) {
    desktop_1920("1920x1080x24", 1920, 1080),
    desktop_1440("1440x900x24",1440,900),
    desktop_1024("1024x768x24",1024,768),
    tablet_ls_768("768x576x24",768,576),
    tablet_p_768("576x768x24", 576, 768),
    phone_ls_320("620x320x24", 620, 320),
    phone_p_320("320x620x24", 320, 620)
}


enum class ScreenDimension (val dimension: Dimension) {
    Desktop_1080 (Dimension(1920, 1080)),
    Desktop_720  (Dimension(1280,720)),
    Phone_small  (Dimension(320, 640)),
    Phone_medium (Dimension(576, 768)),
    Tablet_small (Dimension(768,576))
}

enum class emulatedDevices (val phoneName: String) {
    Galaxy_Note_3("Galaxy Note 3"),
    Nexus_7("Nexus 7"),
    Nexus_10("Nexus 10"),
    Galaxy_S5("Galaxy S5"),
    Pixel_2("Pixel 2"),
    Pixel_2_XL("Pixel 2 XL"),
    iPhone_4("iPhone 4"),
    iPhone_5_SE("iPhone 5/SE"),
    iPhone_6_7_8("iPhone 6/7/8"),
    iPhone_6_7_8_Plus("iPhone 6/7/8 Plus"),
    iPhone_X("iPhone X"),
    iPad("iPad"),
    iPad_Mini("iPad Mini"),
    iPad_Pro("iPad Pro")
}