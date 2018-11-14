package sample

import platform.posix.*

const val TMP_INPUT_JSON_FILE = "danger_in.json"

const val TMP_OUT_JSON_FILE = "danger_out.json"

fun main(args: Array<String>) {

    fopen(TMP_INPUT_JSON_FILE, "wt")?.apply {
        do {
            val line = readLine()?.let {
                fputs(it, this)
            }
        } while (line != null)
    }.also {
        fclose(it)
    }

    "kscript --package Dangerfile.kts".exec()
    "./Dangerfile $TMP_INPUT_JSON_FILE $TMP_OUT_JSON_FILE".exec()
    "echo danger-results:/`pwd`/$TMP_OUT_JSON_FILE".exec()
}

fun String.exec() = system(this)