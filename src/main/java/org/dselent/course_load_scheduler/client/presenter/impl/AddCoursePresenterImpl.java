package org.dselent.course_load_scheduler.client.presenter.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dselent.course_load_scheduler.client.gin.Injector;
import org.dselent.course_load_scheduler.client.model.CourseInfo;
import org.dselent.course_load_scheduler.client.model.Courses;
import org.dselent.course_load_scheduler.client.model.Frequency;
import org.dselent.course_load_scheduler.client.presenter.AddCoursePresenter;
import org.dselent.course_load_scheduler.client.presenter.IndexPresenter;
import org.dselent.course_load_scheduler.client.view.AddCourseView;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.inject.Inject;

public class AddCoursePresenterImpl extends BasePresenterImpl implements AddCoursePresenter{
	//implementations of the helpful functions from the interface
	
	private IndexPresenter parentPresenter;
	private AddCourseView view;
	
	
	@Inject
	public AddCoursePresenterImpl(IndexPresenter parentPresenter, AddCourseView view)
	{
		this.view = view;
		this.parentPresenter = parentPresenter;
		view.setPresenter(this);
		
		//fill the dropdown box
		fillFrequencies();
		
	}
	
	@Override
	public void init()
	{
		bind();
	}

	@Override
	public void bind()
	{
		HandlerRegistration registration;
		
		//TODO: implement event listeners down here
		//registration = eventBus.addHandler(InvalidLoginEvent.TYPE, this);
		//eventBusRegistration.put(InvalidLoginEvent.TYPE, registration);
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.getWidgetContainer());
	}

	//returns the view for the presenter
	@Override
	public AddCourseView getView() {
		return view;
	}
	
	//parent info
	@Override
	public IndexPresenter getParentPresenter()
	{
		return parentPresenter;
	}
	@Override
	public void setParentPresenter(IndexPresenter parentPresenter)
	{
		this.parentPresenter = parentPresenter;
	}
	
	@Override
	public List<Frequency> retrieveFequencies() {
		//TODO: fetch frequencies from the DB
		//**In place of that, sample values are used
		List<Frequency> freqs = new ArrayList<Frequency>();
		
		Frequency f1 = new Frequency();
		f1.setFrequency("1 per year");
		f1.setId(1);
		
		Frequency f2 = new Frequency();
		f2.setFrequency("2 per year");
		f2.setId(2);
		
		Frequency f3 = new Frequency();
		f3.setFrequency("4 per year");
		f3.setId(4);
		
		freqs.add(f1);
		freqs.add(f2);
		freqs.add(f3);
		
		
		return freqs;
	}
	
	
	//gets the frequencies from the database and fills the dropdown with them. 
	@Override
	public void fillFrequencies() {
		List<Frequency> freqs = retrieveFequencies();

		ListBox box = view.getFrequencyDropdown();
		box.clear();
		
		Iterator<Frequency> iterator = freqs.iterator();
		
		
		while(iterator.hasNext()) {
			Frequency f = iterator.next();

			//add to frequency dropdown
			box.addItem(f.getFrequency(), Integer.toString(f.getId()));
		}
		
		view.setFrequencyDropdown(box);
		
	}
	
	@Override
	public void submitNewCourse() {
		int fIndex = view.getFrequencyDropdown().getSelectedIndex();
		
		if(fIndex >= 0) {
			//since index is valid, fill out object
			Courses newCourse = new Courses();
			newCourse.setFrequencyID(Integer.parseInt(view.getFrequencyDropdown().getValue(fIndex)));
			newCourse.setTitle(view.getCourseNameField().getText());
			newCourse.setNumber(view.getCourseNumberField().getText());
			
			//TODO: *DB Event: send out to DB to add!
			Window.alert("If this accesses the DB, it would send a request for a course with Name: "+newCourse.getTitle() +
					", Number: "+newCourse.getNumber() + ", FrequencyId: " + newCourse.getFrequencyID());
			
			
			returnToViewCourses();//returns if course adding was successful or not
		}
		else {//Frequency was not selected
			Window.alert("A Freqeuncy must be selected to create a course.");
		}
	}
	
	
	@Override
	public void returnToViewCourses() {
		
		//TODO:!!!!!!!!! Replace with an Event!
		final Injector injector = Injector.INSTANCE;
		ViewCoursesPresenterImpl viewCoursePresenter = injector.getViewCoursesPresenter();
		viewCoursePresenter.init();
		viewCoursePresenter.go(parentPresenter.getView().getViewRootPanel());
	}
	
	
	
}
