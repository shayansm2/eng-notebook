## set up

```python
import pandas as pd
pd.plotting.register_matplotlib_converters()
import matplotlib.pyplot as plt
%matplotlib inline
import seaborn as sns
```

## matplotlib figure options

```python
# Set the width and height of the figure
plt.figure(figsize=(16, 6))
# Add title
plt.title("Daily Global Streams of Popular Songs in 2017-2018")
# Add label for horizontal axis
plt.xlabel("Date")
# Add label for vertical axis
plt.ylabel("Arrival delay (in minutes)")
# Add legend (show label of multiple lines in of chart)
plt.legend()
```

## matplotlib.puplot various plots

```python
# Line chart
plt.plot(x, y, 'bo-', label='augmented e-constraint')
```

## seaborn various plots

![plots](static/plot.png)

```python
# Line chart
sns.lineplot(data=df)
# Bar chart
sns.barplot(x=df.index, y=df['NK'])
# Scatter plot
sns.scatterplot(x=df['x'], y=df['y'])
# Scatter plot with regression line
sns.regplot(x=df['x'], y=df['y'])
# categorical scatter plot (x-axis is category instead of number)
sns.swarmplot(x=df['x'], y=df['y'])
# Heatmap
sns.heatmap(data=df, annot=True)
# Histogram
sns.histplot(df['x'])
# KDE (kernel density estimate) plots
sns.kdeplot(data=df["x"], shade=True)
# 2D KDE plots
sns.jointplot(x=df['x'], y=df['y'], kind="kde")
# Count Plots
sns.countplot(data=train, x='x')
```

## seaborn figure options

```python
# Set various labels for each line in the chart
sns.lineplot(data=df['Shape of You'], label="Shape of You")
sns.lineplot(data=df['Despacito'], label="Despacito")
# Change the style of the figure to the "dark" theme
# Seaborn has five different themes: (1)"darkgrid", (2)"whitegrid", (3)"dark", (4)"white", and (5)"ticks"
sns.set_style("dark")
```

## seaborn plot options

### Scatter plot

```python
# Color-coded scatter plots
sns.scatterplot(x=df['x'], y=df['y'], hue=df['group'])
# Color-coded scatter plots with regression lines
sns.lmplot(x="x", y="y", hue="group", data=df)
```

### Histograms and KDEs

```python
# Color-coded histograms
sns.histplot(data=df, x='x', hue='group')
# Color-coded KDEs
sns.kdeplot(data=df, x='x', hue='group', shade=True)
```

## subplots

```python
fig, ax = plt.subplots(2, 2, figsize = (10, 8))

fig.set_facecolor('lightgrey')
fig.delaxes(ax[1, 0])

ax[0, 0].plot(x, y, 'r-')
ax[0, 1].plot(x, y, 'b-')
ax[1, 1].plot(x, y, 'g-')
sns.kdeplot(data, x, y, ax=ax[1, 0])
```