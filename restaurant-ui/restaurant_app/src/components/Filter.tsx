import React, {useState, useEffect, useContext} from "react";
import { DataContext } from "../context/DataContext";
import {filterRestaurantRate} from "../service/RestaurantService.ts";
import Restaurant from "Restaurant.tsx";
import { useNavigate } from "react-router-dom";


const restaurantRates = [1, 2, 3, 4, 5];

const Filter = () => {
    const dataContext = useContext(DataContext);
    const [selectedRatings, setSelectedRatings] = useState<number[]>([]);
    const [loading, setLoading] = useState(false);
    const restaurants = dataContext ? dataContext.restaurants : [];
    const navigate = useNavigate();

    useEffect(() => {
        if (selectedRatings.length === 0) {
            dataContext?.setRestaurants([]);
            return;
        }

        setLoading(true);

        Promise.all(selectedRatings.map(rate => filterRestaurantRate(rate)))
            .then((responses) => {
                console.log("API Yanıtları:", responses);

                const mergedRestaurants = responses
                    .filter(res => res && res.data)
                    .flatMap(res => res.data || []);
                console.log("Birleştirilen restoranlar:", mergedRestaurants);
                dataContext?.setRestaurants(mergedRestaurants);
            })
            .catch((err) => {
                console.error("API hatası:", err);
            })
            .finally(() => setLoading(false));

    }, [selectedRatings]);

    const handleRestaurantClick = (id: string) => {
        navigate(`/restaurant/${id}`);
    };

    const handleCheckboxChange = (rate: number) => {
        setSelectedRatings(prev =>
            prev.includes(rate) ? prev.filter(r => r !== rate) : [...prev, rate]
        );
    };

    return (
        <div className="w-screen h-[%100]  flex flex-col items-center pt-5 relative px-20 pb-16">
            <div
                id="recommendationContainer"
                className="flex flex-col justify-center items-center gap-5 min-w-[600px] w-[50%] py-6 h-full bg-gradient-to-br to-gray-900 from-gray-800 rounded-xl shadow-black shadow-xl m-5 p-5 relative text-white"
            >
                <h2 className="text-lg font-bold mb-2 text-pink-400">Filter Restaurants</h2>
                <div className="flex gap-4 mb-4 text-purple-200">
                    {restaurantRates.map(rate => (
                        <label key={rate} className="flex items-center gap-2 cursor-pointer">
                            <input
                                type="checkbox"
                                checked={selectedRatings.includes(rate)}
                                onChange={() => handleCheckboxChange(rate)}
                                className="w-4 h-4"
                            />
                            {rate} ⭐
                        </label>
                    ))}
                </div>

                {loading && <p>Yükleniyor...</p>}

            </div>
            <div className="w-[100%] flex flex-wrap gap-5 justify-center">
            {restaurants.map((restaurant: never) => (
                    <Restaurant
                        key={restaurant.id}
                        restaurant={restaurant}
                        handleRestaurantClick={handleRestaurantClick}
                    />
                ))}
            </div>
        </div>
    );
};

export default Filter;
