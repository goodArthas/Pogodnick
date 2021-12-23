package ram.khab.pogodnick.model

sealed class State {
    object Loading : State()
    object Success : State()
    data class Error(val errorRes: Int) : State()
}