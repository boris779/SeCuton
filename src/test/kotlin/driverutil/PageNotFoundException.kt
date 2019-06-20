package driverutil

import logger

class PageNotFoundException(s: String) : Throwable() {

    val log by logger()

    init {
        log.error(s)
    }


}
