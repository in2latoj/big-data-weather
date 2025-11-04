# big-data-weather

Associated repository to the paper *A visual big data system for the prediction of weather-related variables: Jordan-Spain case study* published in [Multimedia Tools and Applications journal](https://doi.org/10.1007/s11042-020-09848-9) containing the source code of the proposed system.

It is a system designed to deal with high amounts of weather-related data and lets the user analyze those data to perform predictive tasks over the considered variables (temperature and rainfall). The proposed system collects open data and loads them onto a local NoSQL database fusing them at different levels of temporal and spatial aggregation in order to perform a predictive analysis using univariate and multivariate approaches as well as forecasting based on training data from neighbor stations in cases with high rates of missing values.

## Dataset

The dataset used to conduct this study is available as open data in the Global Historical Climatology Network daily (GHCNd) [database](https://www.ncei.noaa.gov/products/land-based-station/global-historical-climatology-network-daily/), published by the National Oceanic and Atmospheric Administration (NOAA), a center of the United States government. However, tu run the system, additional data are required in the form of 4 csv files that can be found in "data" folder of this repository.

## Installation

All the requirements, installation and setup indications can be found in the folder: [`doc`](doc/Installation_and_setup_guide.pdf).

## Usage

A user guide can be found in the folder: [`doc`](doc/User_guide.pdf).

## Repository structure

```
├── data/               # Used Data
├── doc/                # Documentation associated to the project
├── src/                # Source code for the application
├── LICENSE		         # Code license
└── README.md           # Project summary
```

## Reference

Aljawarneh, S., Lara, J.A. & Yassein, M.B. A visual big data system for the prediction of weather-related variables: Jordan-Spain case study. Multimed Tools Appl 82, 13103–13139 (2023). doi: [10.1007/s11042-020-09848-9](https://doi.org/10.1007/s11042-020-09848-9).

```tex
@article{Aljawarneh_2020,
   title={A visual big data system for the prediction of weather-related variables: Jordan-Spain case study},
   volume={82},
   ISSN={1573-7721},
   url={http://dx.doi.org/10.1007/s11042-020-09848-9},
   DOI={10.1007/s11042-020-09848-9},
   number={9},
   journal={Multimedia Tools and Applications},
   publisher={Springer Science and Business Media LLC},
   author={Aljawarneh, Shadi and Lara, Juan A. and Yassein, Muneer Bani},
   year={2020},
   month=oct, pages={13103–13139}}

```



