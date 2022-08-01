package ram.khab.pogodnick.domain.entities

sealed class State {
    object Loading : State()
    object Success : State()
    data class Error(val errorRes: Int) : State()
}
