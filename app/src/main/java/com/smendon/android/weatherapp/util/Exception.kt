package com.smendon.android.weatherapp.util

import java.io.IOException

class RemoteException(msg: String) : IOException(msg)
class NetworkConnectionException(msg: String) : IOException(msg)