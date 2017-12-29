//
// Copyright (C) 2017 Jens Korinth, TU Darmstadt
//
// This file is part of Tapasco (TPC).
//
// Tapasco is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Tapasco is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with Tapasco.  If not, see <http://www.gnu.org/licenses/>.
//
package de.tu_darmstadt.cs.esa.tapasco.itapasco.executables
import  de.tu_darmstadt.cs.esa.tapasco.itapasco.controller._
import  de.tu_darmstadt.cs.esa.tapasco.itapasco.view._
import  scala.swing.{BorderPanel, MainFrame, SimpleSwingApplication}

class Tapasco(mainController: ViewController) extends SimpleSwingApplication {
  import Tapasco._
  javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName)
  javax.swing.UIManager.put("TabbedPane.focus", TABBED_PANE_FOCUS)
  javax.swing.UIManager.put("TabbedPane.selected", TABBED_PANE_SELECTED)
  javax.swing.UIManager.put("TabbedPane.contentAreaColor", TABBED_PANE_CONTENT)

  val mainMenu: AppMenu        = new AppMenu
  val statusPanel: StatusPanel = new StatusPanel

  val mainPanel = new BorderPanel {
    layout(mainController.view) = BorderPanel.Position.Center
    layout(statusPanel) = BorderPanel.Position.South
  }

  def top: MainFrame = new MainFrame {
    title = "Tapasco (TPC)"
    contents = mainPanel
    menuBar = mainMenu
    preferredSize = PREFERRED_SZ
    pack()
    peer.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE)
  }

  override def main(args: Array[String]): Unit = {
    super.main(args)
    while(true) Thread.sleep(MAIN_LOOP_SLEEP_MS)
  }

  private[this] final val MAIN_LOOP_SLEEP_MS = 100000
}

private object Tapasco {
  // scalastyle:off magic.number
  final val PREFERRED_SZ         = new java.awt.Dimension(1280, 1024)
  final val TABBED_PANE_FOCUS    = new java.awt.Color(0, 0, 0, 0)
  final val TABBED_PANE_SELECTED = new java.awt.Color(166, 206, 227)
  final val TABBED_PANE_CONTENT  = new java.awt.Color(166, 206, 227)
  // scalastyle:on magic.number
}
