// JVM_ABI_K1_K2_DIFF: KT-63655
fun box(): String {
    class Local {
        open inner class Inner(val s: String) {
            open fun result() = "Fail"
        }

        val realResult = "OK"

        val obj = object : Inner(realResult) {
            override fun result() = s
        }
    }

    return Local().obj.result()
}
