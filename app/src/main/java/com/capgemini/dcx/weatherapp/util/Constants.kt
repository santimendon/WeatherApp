package com.capgemini.dcx.assisgnment.util


const val API_KEY = "d94705e417e74cfbb34114422213004"

/*
 * @HOST https://www.worldweatheronline.com/
 * @SUBSCRIPTION_TYPE premium/free
 * @API_VERSION v1/v2
 * Base URL for Retrofit object
 */
const val HOST = "http://api.worldweatheronline.com"
const val SUBSCRIPTION_TYPE = "premium"
const val API_VERSION = "v1"

const val BASE_URL = HOST

/**
 * Search results count
 */
const val NUM_OF_RESULTS = 5

/**
 * API response format
 */
const val FORMAT = "json"


/**
 * NETWORK CONNECTION TIMEOUT
 */
const val HTTP_CONNECTION_TIMEOUT: Long = 120

/**
 * NETWORK READ TIMEOUT
 */
const val HTTP_READ_TIMEOUT: Long = 60

/**
 * NETWORK WRITE TIMEOUT
 */
const val HTTP_WRITE_TIMEOUT: Long = 60

/**
 * Database name
 */
const val DATABASE_NAME = "WeatherDatabase.db"
