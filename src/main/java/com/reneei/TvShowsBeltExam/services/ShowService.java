package com.reneei.TvShowsBeltExam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reneei.TvShowsBeltExam.models.Show;
import com.reneei.TvShowsBeltExam.repositories.ShowRepository;

@Service
public class ShowService {

	// need to always remember to inject the repository and create an instance of the repository created
	@Autowired
	private ShowRepository showRepository;
	
	// listing all shows
	public List<Show> allShows() {
		return showRepository.findAll();
	}
	
	// Add a new show
	public Show addShow(Show show) {
		return showRepository.save(show);
	}
	
	// Delete a show
	public void deleteShow(Long id) {
		showRepository.deleteById(id);
	}
	
	// Getting info on one show
	public Show getOneShow(Long id) {
		return showRepository.findById(id).orElse(null);
	}
	
	// Updating info on show
	public Show updateShow(Show show) {
		return showRepository.save(show);
	}
	
}
