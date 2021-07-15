package com.arnold.worldorganizations.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.arnold.worldorganizations.entites.Country;

import java.util.List;

//All the queries in the application are mentioned in these place
@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries ORDER BY id DESC")
    List<Country> getAllCountries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountry(Country country);

    @Query("DELETE FROM countries")
    void deleteAll();
}
