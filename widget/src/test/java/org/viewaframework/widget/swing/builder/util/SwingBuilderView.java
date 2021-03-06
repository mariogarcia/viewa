package org.viewaframework.widget.swing.builder.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import org.viewaframework.swing.builder.SwingBuilder;
import org.viewaframework.swing.builder.layout.GridBagConstraintsBuilder;
import org.viewaframework.view.DefaultViewContainer;

public class SwingBuilderView extends DefaultViewContainer{
	public SwingBuilderView(){
		super("SwingBuilderViewId",
			new SwingBuilder().
				layout(new BorderLayout()).
					component(
						new SwingBuilder().
							layout(new GridBagLayout()).
							 /* (1) label-component */
								label(new GridBagConstraintsBuilder().row(0).col(0).gridWidth(2).build()).
									setName("fromLabel").setText("From").swingBuilder().
								label(new GridBagConstraintsBuilder().row(0).col(2).build()).
									setName("departureDateLabel").setText("Departure Date").swingBuilder().
								text(new GridBagConstraintsBuilder().row(1).col(0).gridWidth(2).weightx(0.8).insets(5,5,5,0).build()).
									setName("from").swingBuilder().
								datePicker(new GridBagConstraintsBuilder().row(1).col(2).fill(GridBagConstraints.REMAINDER).build()).
									setName("departureDate").swingBuilder().
							 /* (2) label-component */
								label(new GridBagConstraintsBuilder().row(2).col(0).gridWidth(2).build()).
									setName("toLabel").setText("To").swingBuilder().
								label(new GridBagConstraintsBuilder().row(2).col(2).build()).
									setName("arrivalDateLabel").setText("Arrival Date").swingBuilder().
								text(new GridBagConstraintsBuilder().row(3).col(0).gridWidth(2).insets(5,5,5,0).build()).
									setName("to").swingBuilder().
								datePicker(new GridBagConstraintsBuilder().row(3).col(2).build()).
									setName("arrivalDate").swingBuilder().
							 /* (3) label-component */
								label(new GridBagConstraintsBuilder().row(4).col(0).build()).
									setName("noPassengersLabel").setText("No Passengers").swingBuilder().
								spinner(new GridBagConstraintsBuilder().row(5).col(0).insets(5,5,5,0).build()).
									setName("noPassengers").swingBuilder().							
							 /* Search Button */
								button(new GridBagConstraintsBuilder().
										row(6).col(0).gridWidth(3).anchor(GridBagConstraints.EAST).fill(GridBagConstraints.NONE).insets(20,0,0,0).build()).
									setName("searchButton").setText("Search").setPreferredSize(new Dimension(180,25)).
							 /* Returning the container to add it to the frame */
						swingBuilder().getTarget()
					,JPanel.class,BorderLayout.NORTH).
				swingBuilder().setPreferredSize(new Dimension(400,0)).getTarget());
	}
}