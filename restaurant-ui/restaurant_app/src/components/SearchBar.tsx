import React, {useState} from "react";
import {searchRestaurantsByName} from "../service/RestaurantService.ts";
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { DataContext } from "../context/DataContext";
import Restaurant from "./Restaurant.tsx";
import Navbar from "./Navbar/Navbar.tsx";
import { FaSearch } from 'react-icons/fa';

function SearchBar() {
    const dataContext = useContext(DataContext);
    const [searchInput, setSearchInput] = useState<string>("");
    const update = dataContext ? dataContext.updateApp : false;
    const restaurants = dataContext ? dataContext.restaurants : [];
    const navigate = useNavigate();

    const handleSearchInput = async () => {
        try {
            if (searchInput === "") {
                dataContext?.setUpdateApp(!update);
            } else {
                const response = await searchRestaurantsByName(searchInput);
                dataContext?.setRestaurants(response.data);
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
                    <div className="flex gap-5">
                        <input
                            type="text"
                            value={searchInput}
                            onChange={(e) => setSearchInput(e.target.value)}
                            className="px-3 py-1 rounded border-none text-purple-200 bg-gray-950"
                            maxLength={20}
                            placeholder="Search"
                        />
                        <button
                            onClick={handleSearchInput}
                            className="px-5 py-3 rounded-lg bg-pink-700 text-white-500"
                        >
                            <FaSearch/>
                        </button>
                    </div>
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
        </div>
    );
}

export default SearchBar;
