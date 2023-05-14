package com.example.hospital.sorting;

import com.example.hospital.dto.Patient;

import java.util.Comparator;

public class AscendingSortByAge implements Comparator<Patient> {

    @Override
    public int compare(Patient o1, Patient o2) {

        return o1.getAge()-o2.getAge();
    }



}
