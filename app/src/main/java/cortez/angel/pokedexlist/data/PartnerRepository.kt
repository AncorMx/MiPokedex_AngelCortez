package cortez.angel.pokedexlist.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import cortez.angel.pokedexlist.data.preferences.PARTNER_FILE
import cortez.angel.pokedexlist.data.preferences.partnerNumberKey
import cortez.angel.pokedexlist.data.preferences.partnerSinceKey
import cortez.angel.pokedexlist.domain.Partner
import java.io.IOException
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private val Context.partnerDataStore: DataStore<Preferences> by preferencesDataStore(PARTNER_FILE)

class PartnerRepository(private val dataStore: DataStore<Preferences>) {

    constructor(context: Context) : this(context.partnerDataStore)

    fun getPartner(email: String): Flow<Partner?> = dataStore.data
        .catch { error ->
            if (error is IOException) emit(emptyPreferences()) else throw error
        }
        .map { preferences ->
            val number = preferences[partnerNumberKey(email)]
            val since = preferences[partnerSinceKey(email)]
            if (number == null || since == null) {
                null
            } else {
                Partner(number, LocalDate.ofEpochDay(since))
            }
        }

    suspend fun savePartner(email: String, partner: Partner) {
        dataStore.edit { preferences ->
            preferences[partnerNumberKey(email)] = partner.number
            preferences[partnerSinceKey(email)] = partner.since.toEpochDay()
        }
    }
}
