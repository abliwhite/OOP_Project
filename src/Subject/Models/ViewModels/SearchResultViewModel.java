package Subject.Models.ViewModels;

import java.util.List;

public class SearchResultViewModel<T> {

	private List<T> searchResultList;

	public SearchResultViewModel(List<T> searchResultList){
		this.searchResultList = searchResultList;
	}
	
	public List<T> getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(List<T> searchResultList) {
		this.searchResultList = searchResultList;
	}
}
