package com.alacrity.giftesttask.exceptions

class NoDataFromResponseException(message: String = "Undefined", exception: Throwable? = null) : GifTestTaskException(message, exception)