import React, { createContext, useState } from "react";

interface DataContextType {
  restaurants: object[];
  setRestaurants: React.Dispatch<React.SetStateAction<object[]>>;
  updateApp: boolean;
  setUpdateApp: React.Dispatch<React.SetStateAction<boolean>>;
  recommendedUser: User;
  setRecommendedUser: React.Dispatch<React.SetStateAction<User>>;
}

interface DataProviderProps {
  children: React.ReactNode;
}

interface Restaurant {
  id: string;
  name: string;
  address: string;
  phone: string;
  email: string;
  description: string;
  website: string;
  workingHours: string;
  "latitude": number;
  "longitude": number;
  "restaurantRate": number;
}

interface User {
  id: number;
  name: string;
  surname: string;
  email: string;
  latitude: number;
  longitude: number;
}

export const DataProvider: React.FC<DataProviderProps> = ({ children }) => {
  const [restaurants, setRestaurants] = useState<Restaurant[]>([]);
  const [updateApp, setUpdateApp] = useState<boolean>(false);
  const [recommendedUser, setRecommendedUser] = useState<User>({
    id: 0,
    name: "string",
    surname: "string",
    email: "string@gmail.com",
    latitude: 0,
    longitude: 0,
  });

  const dataContextValue = {
    restaurants,
    setRestaurants,
    updateApp,
    setUpdateApp,
    recommendedUser,
    setRecommendedUser,
  };

  return (
      <DataContext.Provider value={dataContextValue}>
        {children}
      </DataContext.Provider>
  );
};

export const DataContext = createContext<DataContextType | undefined>(
    undefined
);
