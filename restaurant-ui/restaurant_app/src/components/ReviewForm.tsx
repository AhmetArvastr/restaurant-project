/* eslint-disable @typescript-eslint/no-explicit-any */
import React from "react";
import { Formik, Form, Field } from "formik";
import { saveUserReview } from "../service/UserReviewService";
import { useContext} from "react";
import { DataContext } from "../context/DataContext";

const ReviewForm = ({ restaurantId }: { restaurantId: any }) => {
  const dataContext = useContext(DataContext);

  const saveReview = async (userReview: any) => {
    try {
      saveUserReview(userReview)
        .then((response: any) => {
          return response.data;
        })
        .catch((error: any) => {
          console.log(error);
        })
        .finally(() => {
          dataContext?.setUpdateApp((prev: any) => !prev);
        });
    } catch (error) {
      console.log(error);
      throw error;
    }
  };

  return (
    <div className="max-w-md mx-auto bg-gradient-to-r from-purple-400 via-pink-500 to-red-500 p-6 rounded-lg shadow-lg">
      <Formik
        initialValues={{
          restaurantId: restaurantId,
          comment: "",
          userRate: 0,
          userId: 0,
        }}
        onSubmit={async (values, { setSubmitting }) => {
          await saveReview(values);
          setSubmitting(false);
        }}
      >
        {({ isSubmitting }) => (
          <Form className="space-y-4">
            <div>
              <label
                htmlFor="comment"
                className="block text-lg font-bold text-gray-700"
              >
                Comment
              </label>
              <Field
                as="textarea"
                id="comment"
                name="comment"
                className="mt-1 p-2 w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300"
              ></Field>
            </div>
            <div>
              <label
                htmlFor="userRate"
                className="block text-lg font-bold text-gray-700"
              >
                User Rate
              </label>
              <Field
                as="select"
                id="userRate"
                name="userRate"
                className="mt-1 p-2 w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300"
              >
                <option value="">Select</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
              </Field>
            </div>
            <div>
              <label
                htmlFor="userId"
                className="block text-lg font-bold text-gray-700"
              >
                User ID
              </label>
              <Field
                type="text"
                id="userId"
                name="userId"
                className="mt-1 p-2 w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300"
              ></Field>
            </div>
            <button
              type="submit"
              disabled={isSubmitting}
              className="w-full px-4 py-2 text-white bg-indigo-600 rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              {isSubmitting ? "Submitting..." : "Submit"}
            </button>
          </Form>
        )}
      </Formik>
    </div>
  );
};

export default ReviewForm;