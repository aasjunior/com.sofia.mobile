package com.sofia.mobile.domain.common.utils

import com.sofia.mobile.domain.model.patient.Patient

fun mergeSort(list: List<Patient>): List<Patient> {
    if (list.size <= 1) return list

    val middle = list.size / 2
    val left = list.subList(0, middle)
    val right = list.subList(middle, list.size)

    return merge(mergeSort(left), mergeSort(right))
}

private fun merge(left: List<Patient>, right: List<Patient>): List<Patient> {
    var indexLeft = 0
    var indexRight = 0
    val newList = mutableListOf<Patient>()

    while (indexLeft < left.size && indexRight < right.size) {
        val leftFullName = "${left[indexLeft].firstName} ${left[indexLeft].lastName}"
        val rightFullName = "${right[indexRight].firstName} ${right[indexRight].lastName}"

        if (leftFullName <= rightFullName) {
            newList.add(left[indexLeft])
            indexLeft++
        } else {
            newList.add(right[indexRight])
            indexRight++
        }
    }

    newList.addAll(left.subList(indexLeft, left.size))
    newList.addAll(right.subList(indexRight, right.size))

    return newList
}

