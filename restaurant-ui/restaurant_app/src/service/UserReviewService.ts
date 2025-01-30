/* eslint-disable @typescript-eslint/no-explicit-any */
import { axiosInstance } from "./AxiosInstance";

interface UserReviewPost{
  restaurantId: string,
  comment: string,
  userRate: number,
  userId:  number
}

const fetchUserReviewByRestaurantId = async (id: string): Promise<any> => {
    try {
        const response = await axiosInstance.get<any>(`/api/v1/users/reviews/restaurant/${id}`);
        return response.data;
    } catch (e) {
        console.log(e);
        throw e;
    }
}

const saveUserReview = async (userReview: UserReviewPost): Promise<any> => {
    try {
        const response = await axiosInstance.post<UserReviewPost>(`/api/v1/users/reviews/save`, userReview);
        return response.data;
    } catch (e) {
        console.log(e);
        throw e;
    }

}

export { fetchUserReviewByRestaurantId,saveUserReview}

