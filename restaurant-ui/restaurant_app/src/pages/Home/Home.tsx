/* eslint-disable @typescript-eslint/no-explicit-any */
import Navbar from "../../components/Navbar/Navbar";
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { DataContext } from "../../context/DataContext";
import {
    fetchAllRestaurants,
    restaurantRecommendation,
} from "../../service/RestaurantService";
import Restaurant from "../../components/Restaurant";

function Home() {
    const dataContext = useContext(DataContext);
    const [recommendationInput, setRecommendationInput] = useState<string>("");
    const update = dataContext ? dataContext.updateApp : false;
    const restaurants = dataContext ? dataContext.restaurants : [];
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetchAllRestaurants();
                console.log(response.data);
                dataContext?.setRestaurants(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        if (dataContext) {
            fetchData();
        }
    }, [update]);

    const handleRecommendation = async () => {
        try {
            if (recommendationInput === "") {
                dataContext?.setRecommendedUser({
                    id: 0,
                    name: "string",
                    surname: "string",
                    email: "string@gmail.com",
                    latitude: 0,
                    longitude: 0,
                });
                dataContext?.setUpdateApp(!update);
            } else {
                const response = await restaurantRecommendation(recommendationInput);
                dataContext?.setRestaurants(response.data.restaurantList);
                dataContext?.setRecommendedUser({
                    id: response.data.user.id,
                    name: response.data.user.name,
                    surname: response.data.user.surname,
                    email: response.data.user.email,
                    latitude: response.data.user.latitude,
                    longitude: response.data.user.longitude,
                });
            }
        } catch (e) {
            console.log(e);
        }
    };

    const handleRestaurantClick = (id: string) => {
        navigate(`/restaurant/${id}`);
    };

    return (
        <div className="w-screen h-[%100]  flex flex-col items-center pt-5 relative px-20 pb-16">
            <Navbar />
            <div
                id="innerHomeContainer"
                className="mt-48 flex flex-col flex-wrap gap-5 justify-center items-center"
            >
                <div
                    id="recommendationContainer"
                    className="flex flex-col justify-center items-center gap-5 min-w-[600px] w-[50%] py-6 h-full bg-gradient-to-br to-gray-900 from-gray-800 rounded-xl shadow-black shadow-xl m-5 p-5 relative text-white"
                >
                    <h2 className="font-bold text-lg text-pink-400">
                        Get Restaurant Recommendation Special To You
                    </h2>
                    <div className="flex gap-5">
                        <input
                            type="text"
                            value={recommendationInput}
                            onChange={(e) => setRecommendationInput(e.target.value)}
                            className="px-3 py-1 rounded border-none text-purple-200 bg-gray-950"
                            maxLength={10}
                            placeholder="Please enter your user id"
                        />
                        <button
                            onClick={handleRecommendation}
                            className="px-5 py-3 rounded-lg bg-pink-700 text-white-500"
                        >
                            Get Recommended Restaurants
                        </button>
                    </div>
                </div>
                <div className="mb-10">
                    {dataContext?.recommendedUser.id !== 0 && (
                        <div>
                            <h1 className="font-bold text-lg text-pink-400">
                                Restaurant Recommendations For{" "}
                                {dataContext?.recommendedUser.name}
                            </h1>
                        </div>
                    )}
                </div>
                <div className="w-[100%] flex flex-wrap gap-5 justify-center">
                    {restaurants.map((restaurant: any) => (
                        <Restaurant
                            key={restaurant.id}
                            restaurant={restaurant}
                            handleRestaurantClick={handleRestaurantClick}
                        />
                    ))}
                </div>
            </div>
        </div>
    );
}

export default Home;
