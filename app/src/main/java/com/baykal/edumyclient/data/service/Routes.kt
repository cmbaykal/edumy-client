package com.baykal.edumyclient.data.service

object Routes{

    private const val BASE_URL = "http://192.168.0.109:8080/"

    const val registerUser = "${BASE_URL}user/register"
    const val loginUser = "${BASE_URL}user/login"
    const val updateUser = "${BASE_URL}user/update"
    const val userInfo = "${BASE_URL}user/info"

    const val addClass = "${BASE_URL}class/add"
    const val classroomInfo = "${BASE_URL}class/info"
    const val assignUser = "${BASE_URL}class/assign"
    const val leaveUser = "${BASE_URL}class/leave"

}