import React, { useEffect, useState } from "react";
import RecommendationListItem from "./RecommendationListItem";
import fetchRecommendations from "./fetchRecommendations";

export default function MessageList() {
  const [recommendations, setRecommendations] = useState([]);

  useEffect(() => {
    fetchRecommendations().then((fetchedRecommendations) => setRecommendations(fetchedRecommendations));
  }, []);

  return (
    <div>
      <h1>Recommendations</h1>
      <table className="table">
        <tbody>
            <tr>
                <th>Title</th>
                <th>Link</th>
                <th>Description</th>
                <th>Added on</th>
                <th>Category</th>
                <th>Actions</th>
            </tr>
            {recommendations.map((recommendation) => (
                <RecommendationListItem recommendation={recommendation} key={recommendation.id} />
            ))}
        </tbody>
      </table>

      
       
      

      <a className="btn btn-primary" href="/add">
        Add a recommendation
      </a>
    </div>
  );
}
