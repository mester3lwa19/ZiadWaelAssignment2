#business question we want to answer
#1) each genre and its percentage from the total profit
#2) how was the movie industry evolving across the years
#3) top 10 directors acording to the profit of thier movies
#4) how likely each genre may preform and get a high score


import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns


film_details_df = pd.read_csv('D:/Data analysis projects/Data sets/FilmDetails.csv')
more_info_df = pd.read_csv('D:/Data analysis projects/Data sets/MoreInfo.csv')
movies_df = pd.read_csv('D:/Data analysis projects/Data sets/Movies.csv')

# Merging the data sets in a one dataset
Movies = pd.merge(film_details_df, movies_df, on='id', suffixes=('_details', '_movies'))
#getting info about the data set
print(Movies.info)
countingNull=Movies.isnull().sum()
print(countingNull)      #see the coulumns that have a null values
Movies=Movies.dropna()  #deleting the null values coulumns

# Convert budget and revenue coulumns from a string to numeric
Movies['budget_usd'] = pd.to_numeric(Movies['budget_usd'], errors='coerce')
Movies['revenue_usd'] = pd.to_numeric(Movies['revenue_usd'], errors='coerce')

# Calculate a profit coulmms
Movies['profit'] = Movies['revenue_usd'] - Movies['budget_usd']

#convert the realease year form string to a date time object
Movies['release_year'] = pd.to_datetime(Movies['release_date'], errors='coerce').dt.year

# Spliting genres to multiple genres
genres_split_df = Movies.assign(genres=Movies['genres'].str.split(', ')).explode('genres')
#groubing the genres and calculating its profit
GenreProfit = genres_split_df.groupby('genres')['profit'].agg(['sum']).sort_values(by='sum', ascending=False)

# Top genres
TopGenre = GenreProfit.head(10)
labels = TopGenre.index
sizes = TopGenre['sum']

# Grouping the years and calculating its profit
yearly_profit = Movies.groupby('release_year')['profit'].sum().sort_index()

# calculating the profit for each director and getting the top 10's
top_directors = Movies.groupby('director')['profit'].sum().sort_values(ascending=False).head(10)

# grouping the genres by the average of the user score of each movie
genre_rating = genres_split_df.groupby('genres')['user_score'].mean().sort_values(ascending=False)


color_palette = sns.color_palette("dark:#5A9_r", n_colors=6)  # Dark color palette

#DASHBOARD
fig, axs = plt.subplots(2, 2, figsize=(20, 20), gridspec_kw={'height_ratios': [1, 1]})

#Pie chart for Top 10 Genres by Total Profit
axs[0, 0].pie(sizes, labels=labels, autopct='%1.1f%%', startangle=140, colors=color_palette[:len(sizes)])
axs[0, 0].set_title('Top 10 Genres by Total Profit (%)', fontsize=16, color='white')

#Line chart for yearly profit
years = yearly_profit.index
profits = yearly_profit.values
axs[0, 1].plot(years, profits, color=color_palette[0], marker='o', linestyle='-', linewidth=2)
axs[0, 1].set_title('Yearly Profit Trends', fontsize=16, color='black')
axs[0, 1].set_xlabel('Year', fontsize=12, color='black')
axs[0, 1].set_ylabel('Total Profit (USD)', fontsize=12, color='black')
axs[0, 1].tick_params(axis='x', rotation=45, labelcolor='black')
axs[0, 1].grid(color='gray', linestyle='--', linewidth=0.5, alpha=0.7)

#Bar chart for Top 10 Directors by Total Profit
axs[1, 0].bar(top_directors.index, top_directors.values, color=color_palette[1])
axs[1, 0].set_title('Top 10 Directors by Total Profit', fontsize=16, color='black')
axs[1, 0].set_xlabel('Directors', fontsize=12, color='black')
axs[1, 0].set_ylabel('Total Profit (USD)', fontsize=12, color='black')
axs[1, 0].tick_params(axis='x', rotation=45, labelcolor='black')

#Bar chart for Average User Rating by Genre
genre_rating.plot(kind='bar', color=color_palette[2], ax=axs[1, 1], figsize=(12, 6))
axs[1, 1].set_ylabel('Average User Rating', fontsize=12, color='black')
axs[1, 1].tick_params(axis='x', rotation=45, labelcolor='black')


plt.subplots_adjust(hspace=0.4)  # Adjust vertical spacing between rows
#displaying
plt.show()

