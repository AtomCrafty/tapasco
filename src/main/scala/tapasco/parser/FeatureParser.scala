package de.tu_darmstadt.cs.esa.tapasco.parser
import  de.tu_darmstadt.cs.esa.tapasco.base._

private object FeatureParser {
  import CommandLineParser._

  /** Returns a parser for Seq[Feature]. */
  def apply(): Parser[Seq[Feature]] = features

  /* @{ Features */
  private def features: Parser[Seq[Feature]] =
    param("features", false) ~> rep1sep(featureLed | featureOled | featureCache | featureDebug |
        featureBlueDma | featureAtsPri, LIST_SEP)

  private def featureLed: Parser[Feature] = """^(?i)led""".r ^^ { _ => Feature.LED(true) }

  private def featureOled: Parser[Feature] = """^(?i)oled""".r ^^ { _ => Feature.OLED(true) }

  private def featureCache: Parser[Feature] =
    """^(?i)cache""".r ~> ("(" ~>
        ("""(?i)size""".r ~> "=" ~> wholeNumber) ~ "," ~
        ("""(?i)associativity""".r ~> "=" ~> wholeNumber)
      <~ ")") ^^ { p => Feature.Cache(true, p._1._1.toInt, p._2.toInt) }

  private def featureDebug: Parser[Feature] = (
    """^(?i)debug""".r ~> opt("(" ~> repsep(
      ("""^(?i:depth)?""".r       ~> "=" ~> wholeNumber ^^ { p => ("depth", p) })   |
      ("""^(?i:stages)?""".r      ~> "=" ~> wholeNumber ^^ { p => ("stages", p) })  |
      ("""^(?i:defaults)?""".r    ~> "=" ~> boolLiteral ^^ { p => ("defaults", p.toString)}) |
      (("""^(?i:nets)?""".r        ~> "=" ~> "[" ~>
        rep1sep(stringLiteral ^^ { _.stripPrefix("\"").stripSuffix("\"") }, LIST_SEP)
      <~ "]") ^^ { p => ("nets", p mkString ":") }),
      LIST_SEP)
    <~ ")")
  ) ^^ { p =>
    val m: Option[Map[String, String]] = p map (_.toMap)
    Feature.Debug(
      enabled = true,
      depth = m flatMap (_.get("depth") map (_.toInt)),
      stages = m flatMap (_.get("stages") map (_.toInt)),
      useDefaults = m flatMap (_.get("defaults") map (_.toBoolean)),
      nets = m flatMap (_.get("nets") map (_.split(":")))
    )
  }

  private def featureBlueDma: Parser[Feature] = """^(?i)bluedma""".r ^^ { _ => Feature.BlueDma(true) }

  private def featureAtsPri: Parser[Feature] = """^(?i)ats\+?pri""".r ^^ { _ => Feature.AtsPri(true) }
  /* Features @} */
}
// vim: foldmarker=@{,@} foldmethod=marker foldlevel=0