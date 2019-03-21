package driverutil

object WebDriverSessionStore {

    private val store = HashMap<String, WebDriverSession>()

    @Synchronized
    fun get(sessionName: String): WebDriverSession {

        if (!store.containsKey(sessionName)) {
            store[sessionName] = WebDriverSession(sessionName)
        }

        return store.get(sessionName)!!
    }

    fun remove(sessionName: String) {
        store.remove(sessionName)?.webDriver?.quit()
    }

}