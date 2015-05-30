package com.wesovi.demo.util

import com.typesafe.config.ConfigFactory

trait ConfigHolder { 
  val config = ConfigFactory.load()
}