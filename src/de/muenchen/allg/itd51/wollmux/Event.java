/*
 * Dateiname: Event.java
 * Projekt  : WollMux
 * Funktion : Repräsentiert ein für den WollMux relevantes Event.
 * 
 * Copyright: Landeshauptstadt München
 *
 * Änderungshistorie:
 * Datum      | Wer | Änderungsgrund
 * -------------------------------------------------------------------
 * 24.10.2005 | LUT | Erstellung
 * 01.12.2005 | BNK | +ON_UNLOAD (mit Nummer 70, folgen die Nummern irgendeinem System?)
 * -------------------------------------------------------------------
 *
 * @author Christoph Lutz (D-III-ITD 5.1)
 * @version 1.0
 * 
 */
package de.muenchen.allg.itd51.wollmux;


/**
 * Diese Klasse repräsentiert ein WollMux-Ereignis. Alle WollMux-Ereignisse
 * werden in einem eigenen EventProcessorThread vom WollMux sequentiell
 * ausgeführt. Jedes Event hat einen Namen (bzw. Typ) (z.B. Event.ON_LOAD), ein
 * optionales String-Argument und eine optionale Quelle (die das Ereignis
 * verursacht hat).
 * 
 * @author Christoph Lutz (D-III-ITD 5.1)
 * 
 */
public class Event
{
  // Konstanten für alle bekannten events:

  public static final int UNKNOWN = -1;

  public static final int ON_LOAD = 1;

  public static final int ON_NEW = 2;

  public static final int ON_MODIFIED = 10;

  public static final int ON_FOCUS = 12;

  public static final int ON_ABSENDER_AUSWAEHLEN = 20;

  public static final int ON_PERSOENLICHE_ABSENDERLISTE = 21;

  public static final int ON_DATENSATZ_BEARBEITEN = 22;

  public static final int ON_DIALOG_BACK = 30;

  public static final int ON_DIALOG_ABORT = 31;

  public static final int ON_OPENTEMPLATE = 40;

  public static final int ON_SELECTION_CHANGED = 50;

  public static final int ON_INITIALIZE = 60;
  
  public static final int ON_FRAME_CHANGED = 70;

  // private Felder:

  private int event = UNKNOWN;

  private Object source = null;

  private String argument = "";

  /**
   * Diese Methode liefert eine String-Repräsentation des Eventnames zurück.
   * 
   * @return eine String-Repräsentation des Eventnames
   */
  private String getEventName()
  {
    if (event == UNKNOWN)
    {
      return "UNKNOWN";
    }
    if (event == ON_ABSENDER_AUSWAEHLEN)
    {
      return "ON_ABSENDER_AUSWAEHLEN";
    }
    if (event == ON_PERSOENLICHE_ABSENDERLISTE)
    {
      return "ON_PERSOENLICHE_ABSENDERLISTE";
    }
    if (event == ON_DATENSATZ_BEARBEITEN)
    {
      return "ON_DATENSATZ_BEARBEITEN";
    }
    if (event == ON_LOAD)
    {
      return "ON_LOAD";
    }
    if (event == ON_FRAME_CHANGED)
    {
      return "ON_FRAME_CHANGED";
    }
    if (event == ON_NEW)
    {
      return "ON_NEW";
    }
    if (event == ON_MODIFIED)
    {
      return "ON_MODIFIED";
    }
    if (event == ON_OPENTEMPLATE)
    {
      return "ON_OPENTEMPLATE";
    }
    if (event == ON_DIALOG_BACK)
    {
      return "ON_DIALOG_BACK";
    }
    if (event == ON_DIALOG_ABORT)
    {
      return "ON_DIALOG_ABORT";
    }
    if (event == ON_SELECTION_CHANGED)
    {
      return "ON_SELECTION_CHANGED";
    }
    if (event == ON_INITIALIZE)
    {
      return "ON_INITIALIZE";
    }
    if (event == ON_FOCUS)
    {
      return "ON_FOCUS";
    }
    else
      return "namenlos";
  }

  /**
   * Der Konstruktor erzeugt ein neues Event mit dem Eventnamen event und ohne
   * Argument und Quelle.
   * 
   * @param event
   *          der Typ des Events. Siehe die Konstantendefinition in Event (z.B.
   *          Event.ON_LOAD)
   */
  public Event(int event)
  {
    this.event = event;
  }

  /**
   * Der Konstruktor erzeugt ein neues Event mit dem Namen event und dem
   * String-argument argument.
   * 
   * @param event
   *          der Name des Events. Siehe die Konstantendefinition in Event (z.B.
   *          Event.ON_LOAD)
   * @param argument
   *          Ein beliebiger String als Argument, der von dem dem Event
   *          zugehörigen Eventhandler interpretiert werden muss.
   */
  public Event(int event, String argument)
  {
    this.event = event;
    this.argument = argument;
  }

  /**
   * Der Konstruktor erzeugt ein neues Event mit dem Namen event, dem
   * String-argument argument und der Quelle source.
   * 
   * @param event
   * @param argument
   * @param source
   */
  public Event(int event, String argument, Object source)
  {
    this.event = event;
    this.argument = argument;
    this.source = source;
  }

  /**
   * Diese Methode liefert den Namen des Events in Form eines integer-Wertes
   * zurück. Die gültigen Namen sind in den Konstanten Event.ON_* abgelegt.
   * 
   * @return Name des Events (siehe Event-Konstanten z.B. Event.ON_LOAD)
   */
  public int getEvent()
  {
    return event;
  }

  /**
   * Diese Methode liefert die Quelle des Events zurück, die das Event initiiert
   * hat. Bei Events, die aus OOo kamen ist die Quelle üblicherweise vom Typ
   * XComponent.
   * 
   * @return die Quelle aus der das Event kam.
   */
  public Object getSource()
  {
    return source;
  }

  /**
   * Diese Methode liefert das String-Argument des Events zurück.
   * 
   * @return das String-Argument des Events
   */
  public String getArgument()
  {
    return argument;
  }

  /**
   * Diese Methode erzeugt eine String-Repräsentation des Event-Objekts mit der
   * Syntax "Event(<Eventname>)".
   * 
   * @see java.lang.Object#toString()
   */
  public String toString()
  {
    return "Event(" + getEventName() + ")";
  }
}
