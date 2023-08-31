package in.ineuron.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.dao.ITouristDao;
import in.ineuron.exception.TouristNotFoundException;
import in.ineuron.model.Tourist;

@Service
public class TouristServiceImpl implements ITouristService {
	
	@Autowired
	private ITouristDao repo;

	@Override
	public String registerTourist(Tourist tourist) {
		// TODO Auto-generated method stub
		Integer tid = repo.save(tourist).getTid();
		return "Tourist is saved with the id :: "+ tid ;
	}

	@Override
	public List<Tourist> fetchAllTourist() {
		// TODO Auto-generated method stub
		List<Tourist> list = repo.findAll();
		return list;
	}

	@Override
	public Tourist fetchTouristById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Tourist> optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
			
		}else {
			throw new TouristNotFoundException(" tourist with id "+id+" not found");
		}
		
	}

	@Override
	public String updateTouristByDetails(Tourist tourist) {
		// TODO Auto-generated method stub
		Optional<Tourist> optional = repo.findById(tourist.getTid());
		if(optional.isPresent()) {
			repo.save(tourist);
			return "Tourist is updated with the id :: "+ tourist.getTid();
			
		}else
		{
			throw new TouristNotFoundException(
					"tourist with the id:: " + tourist.getTid() + " not available for updation");
		}
	}

	@Override
	public String updateTouristById(Integer id, Float hikePercent) {
		// TODO Auto-generated method stub
		Optional<Tourist> optional = repo.findById(id);
		if(optional.isPresent()) {
			Tourist tourist = optional.get();
			tourist.setBudget(tourist.getBudget()+ (tourist.getBudget() * (hikePercent / 100)));
			repo.save(tourist);
			return "Tourist budget is updated for the id :: " + tourist.getTid();
		}
		else {
			throw new TouristNotFoundException("Tourist not found for the id " + id);
		}

	}

	@Override
	public String deleteTouristById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Tourist> optional = repo.findById(id);
		if(optional.isPresent()) {
			repo.deleteById(id);
			return "Tourist is deleted with the id "+ id;
			
		}
		else {
			throw new TouristNotFoundException("Tourist not found for the id " + id);
		}
	}

}
