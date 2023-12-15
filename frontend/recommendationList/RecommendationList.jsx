import React, { useEffect, useState } from "react";
import RecommendationListItem from "./RecommendationListItem";
import fetchRecommendations from "./fetchRecommendations";
import fetchCategories from "./fetchCategories";
import fetchRecommendationbyCategoryId from "./fetchRecommendationbyCategoryId";
import fetchCurrentUser from "./fetchCurrentUser";

export default function RecommendationList() {
	const [categories, setCategories] = useState([]);
	const [selectedCategoryId, setSelectedCategoryId] = useState("any");
	const [recommendations, setRecommendations] = useState([]);
	const [currUser, setCurrUser] = useState(null);
	const [showButton, setShowButton] = useState(false);

	useEffect(() => {
		fetchCategories().then((fetchedCategories) =>
			setCategories(fetchedCategories)
		);
	}, []);

	useEffect(() => {
		if (selectedCategoryId !== "any") {
			fetchRecommendationbyCategoryId(selectedCategoryId).then(
				(fetchedRecommendations) => setRecommendations(fetchedRecommendations)
			);
		} else {
			fetchRecommendations().then((fetchedRecommendations) =>
				setRecommendations(fetchedRecommendations)
			);
		}
	}, [selectedCategoryId]);

	console.log(currUser);

	useEffect(() => {
		fetchCurrentUser(setCurrUser, setShowButton);
		/*const getCurrUser = async () => {
			setShowButton(false);
			setCurrUser(null);
			try {
				
				const response = await fetch(
					"/api/users/current"
				);
				if (!response.ok) {
					throw new Error(
						"Something went wrong: " + response.statusText
					);
					
				}
				let userData = await response.json();
				setCurrUser(userData);
				setAddButton(true);
			} catch(err) {
				console.log(err);
				setCurrUser(null);
				setShowButton(false);
			}
			
		}
		getCurrUser()
		*/
					
	},[])

	function handleCategoryFilterChange(event) {
		setSelectedCategoryId(event.target.value);
	}

	const handleDelete = (deleteRecommendation) => {
		if (window.confirm(`Delete reading recommendation "${deleteRecommendation.title}"?`)) {
			fetch(`/delete/${deleteRecommendation.id}`, {
				method: 'POST',
				headers: {
					"X-CSRF-TOKEN": document.getElementById("_csrf").getAttribute("content"),
				},
			}).then(response => {
				if (response.ok) {
					setRecommendations(recommendations.filter((recommendation) => recommendation.id !== deleteRecommendation.id))
				} else {
					throw new Error("error in deletion: " + response.statusText);
				}
			})
			.catch(err => console.error(err));
		}
	};

	// Function to navigate to the signup page
	const navigateToSignUp = () => {
		window.location.href = '/signup'; // path to the signup page
	};

	return (
		<div>
			<h1>Recommendations</h1>
			<div className="mb-3">
				<label className="form-label">Filter by a category</label>
				<select
					className="form-select"
					onChange={handleCategoryFilterChange}
					value={selectedCategoryId}
				>
					<option value="any">Any category</option>
					{categories.map((category) => (
						<option value={category.id} key={category.id}>
							{category.name}
						</option>
					))}
				</select>
			</div>

			<table className="table">
				<tbody>
					<tr>
						<th>Title</th>
						<th>Link</th>
						<th>Description</th>
						<th>Added on</th>
						<th>Category</th>
						<th>Added by</th>
						<th>Actions</th>
					</tr>
					{recommendations.map((recommendation) => (
						<RecommendationListItem
							recommendation={recommendation}
							key={recommendation.id}
							handleDelete={handleDelete}
							currUser={currUser}
						/>
					))}
				</tbody>
			</table>
			<div>
				{showButton && 
					<a className="btn btn-primary" href="/add">
						Add a recommendation
					</a>
				}
			</div>
			
		</div>
	);
}
