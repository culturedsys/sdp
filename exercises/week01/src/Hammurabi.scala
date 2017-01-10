import scala.util.Random

object Hammurabi {
  def printIntroductoryStatement(): Unit = println(
    """Congratulations, you are the newest ruler of ancient Samaria, elected
  for a ten year term of office. Your duties are to dispense food, direct
  farming, and buy and sell land as needed to support your people. Watch
  out for rat infestations and the plague! Grain is the general currency,
  measured in bushels. The following will help you in your decisions:
  * Each person needs at least 20 bushels of grain per year to survive.
  * Each person can farm at most 10 acres of land.
  * It takes 2 bushels of grain to farm an acre of land.
  * The market price for land fluctuates yearly.
  Rule wisely and you will be showered with appreciation at the end of
  your term. Rule poorly and you will be kicked out of office!
  """)

  def hammurabi(): Unit = {
    var starved = 0         // How many people starved
    var immigrants = 5      // How many people came to the city
    var population = 100
    var harvest = 3000      // Total bushels harvested
    var bushelsPerAcre = 3  // Amount harvested per acre planted
    var ratsAte = 200      // Bushels destroyed by rats
    var bushelsInStorage = 2800
    var acresOwned = 1000
    var pricePerAcre = 19   // Each acre costs this many bushels
    var plagueDeaths = 0
    var deposed = false;

    printIntroductoryStatement()

    for (year <- 1 to 10 if !deposed) {
      println("Oh great Hammurabi!")
      println("You are in year " + year + " of your ten year rule")
      println("In the previous year " + starved + " people starved to death")
      println("In the previous year " + immigrants + " people entered the kingdom")
      println("The population is now " + population)
      println("We harvested " + harvest + " bushels at " + bushelsPerAcre + " bushels per acre")
      println("Rats destroyed " + ratsAte + " bushels, leaving " + bushelsInStorage + " bushels in storage")
      println("The city owns " + acresOwned + " acres of land")
      println("Land is currently worth " + pricePerAcre + " bushels per acre")
      println("There were " + plagueDeaths + " deaths from plague")

      val acresToBuy = askHowMuchLandToBuy(bushelsInStorage, pricePerAcre)
      acresOwned += acresToBuy
      bushelsInStorage -= acresToBuy * pricePerAcre

      if (acresToBuy == 0) {
        val acresToSell = askHowMuchLandToSell(acresOwned)
        acresOwned -= acresToSell
        bushelsInStorage += acresToSell * pricePerAcre
      }

      val bushelsToFeed = askHowMuchGrainToFeedToThePeople(bushelsInStorage)
      bushelsInStorage -= bushelsToFeed

      val acresToPlant = askHowManyAcresToPlant(population, bushelsInStorage)
      bushelsInStorage -= 2 * acresToPlant

      plagueDeaths = calculatePlagueDeaths(population)
      population -= plagueDeaths

      starved = calculateStarvation(population, bushelsToFeed)

      if ((starved / population) > .45) {
        deposed = true
      }

      population -= starved

      if (starved == 0) {
        immigrants = calculateImmigrants(acresOwned, bushelsInStorage, population)
      } else {
        immigrants = 0
      }

      population += immigrants

      bushelsPerAcre = calculateBushelsPerAcre()
      harvest = bushelsPerAcre * acresToPlant

      bushelsInStorage += harvest

      ratsAte = calculateRatEating(bushelsInStorage)
      bushelsInStorage -= ratsAte

      pricePerAcre = calculatePricePerAcre()
    }

    if (deposed) {
      println("You were deposed. Not so great, Hammurabi")
    } else {
      if (acresOwned > 2000 && population > 100) {
        println("Oh Great Hammurabi, your lands have more than doubled and your population has increased")
      } else if (acresOwned > 1000 && population > 50) {
        println("Oh Mediocre Hammurabi, your lands have increased, but at what cost to your subjects?")
      } else {
        println("Oh Terrible Hammurabi, the kingdom has whithered under your rule")
      }
    }
  }

  def askHowMuchLandToBuy(bushelsAvailable: Int, price: Int): Int = {
    var acresToBuy = readInt("How many acres will you buy? ")
    while (acresToBuy < 0 || (acresToBuy * price) > bushelsAvailable) {
      println("O Great Hammurabi, we have but " + bushelsAvailable + " bushels of grain!")
      acresToBuy = readInt("How many acres will you buy? ")
    }
    acresToBuy
  }

  def askHowMuchLandToSell(acresOwned: Int): Int = {
    var acresToSell = readInt("How many acres will you sell? ")
    while (acresToSell > acresOwned) {
      println("O Great Hammurabi, we own but " + acresOwned + " acres of land!")
      acresToSell = readInt("How many acres will you sell? ")
    }
    acresToSell
  }

  def askHowMuchGrainToFeedToThePeople(bushelsInStorage: Int): Int = {
    var bushelsToFeed = readInt("How much grain will you feed to the people? ")
    while (bushelsToFeed > bushelsInStorage) {
      println("O Great Hammurabi, we have but " + bushelsInStorage + " bushes of grain!")
      bushelsToFeed = readInt("How much grain will you feed to the people? ")
    }
    bushelsToFeed
  }

  def askHowManyAcresToPlant(population: Int, bushelsInStorage: Int): Int = {
    val acresToPlant = readInt("How many acres will you plant? ")
    if (acresToPlant > population * 10) {
      println("Oh Great Hammurabi, we have but " + population + " people!")
      askHowManyAcresToPlant(population, bushelsInStorage)
    } else if (acresToPlant > bushelsInStorage / 2) {
      println("Oh Great Hammurabi, we have but " + bushelsInStorage + " bushels of grain!")
      askHowManyAcresToPlant(population, bushelsInStorage)
    } else {
      acresToPlant
    }
  }

  def calculatePlagueDeaths(population: Int): Int = {
    if (Random.nextInt(100) < 15) {
      population / 2
    } else {
      0
    }
  }

  def calculateStarvation(population: Int, bushelsFed: Int): Int = {
    population - bushelsFed / 20
  }

  def calculateImmigrants(acresOwned: Int, bushelsInStorage: Int, population: Int) = {
    (20 * acresOwned + bushelsInStorage) / (100 * population) + 1
  }

  def calculateBushelsPerAcre(): Int = {
    Random.nextInt(8) + 1
  }

  def calculateRatEating(bushelsInStorage: Int): Int = {
    if (Random.nextInt(100) < 40) {
      val ratPortion = Random.nextInt(3) + 1
      (bushelsInStorage.toDouble * (ratPortion / 10.0)).toInt
    } else {
      0
    }

  }

  def calculatePricePerAcre(): Int = Random.nextInt(7) + 17

  def readInt(message: String): Int = {
    try {
      readLine(message).toInt
    } catch {
      case _ : Throwable =>
        println("That's not an integer. Please enter an integer")
        readInt(message)
    }
  }
}

Hammurabi.hammurabi()