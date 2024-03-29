package leftshiftone.intentmarkup

data class IntentMarkup(val autocomplete: Boolean, val text:String, val musts: List<MustWord>, val keyword: Boolean)
data class MustWord(val text: String, val fuzzy: Boolean)