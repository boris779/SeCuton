package driverutil

enum class DriverType {
    CHROME,
    FIREFOX,
    OPERA,
    EDGE,
    IE,
    REMOTE_CHROME,
    REMOTE_FIREFOX,
    REMOTE_ANDROID,
    ANDROID_DEVICE,
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