/**
 *                    
 * @author greg (at) myrobotlab.org
 *  
 * This file is part of MyRobotLab (http://myrobotlab.org).
 *
 * MyRobotLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version (subject to the "Classpath" exception
 * as provided in the LICENSE.txt file that accompanied this code).
 *
 * MyRobotLab is distributed in the hope that it will be useful or fun,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * All libraries in thirdParty bundle are subject to their own license
 * requirements - please refer to http://myrobotlab.org/libraries for 
 * details.
 * 
 * Enjoy !
 * 
 * */

package org.myrobotlab.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.myrobotlab.logging.LoggerFactory;
import org.myrobotlab.service.Blender;
import org.myrobotlab.service.Swing;
import org.slf4j.Logger;

public class BlenderGui extends ServiceGui implements ActionListener {

  static final long serialVersionUID = 1L;
  public final static Logger log = LoggerFactory.getLogger(BlenderGui.class);

  JButton connect = new JButton("connect");

  public BlenderGui(final String boundServiceName, final Swing myService, final JTabbedPane tabs) {
    super(boundServiceName, myService, tabs);

    display.setLayout(new BorderLayout());
    JPanel north = new JPanel();
    north.add(connect);
    connect.addActionListener(this);
  
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    Object o = event.getSource();
    if (o == connect) {
      send("connect");
    }

  }

  @Override
  public void subscribeGui() {
    // commented out subscription due to this class being used for
    // un-defined gui's

    subscribe("publishState", "onState", Blender.class);
    subscribe("isConnected", "onConnected", Boolean.class);
    send("publishState");
  }

  @Override
  public void unsubscribeGui() {
    // commented out subscription due to this class being used for
    // un-defined gui's

    unsubscribe("publishState", "onState", Blender.class);
  }

  public void onState(Blender template) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {

      }
    });
  }

  public void onConnected() {
    connect.setText("disconnect");
  }

}
