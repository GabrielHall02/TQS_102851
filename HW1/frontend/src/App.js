import "./App.css";

import React from "react";
import { Autocomplete, Table, TableHead, TableBody, TableRow, TableCell, Button  } from "@mui/material";
import TextField from "@mui/material/TextField";
import countries from "iso-3166-country-list";
import { Box } from "@mui/system";

const countryList = countries.map((country) => country.name);

function App() {
	function getAirPollution(country, city) {
		// Get country code from country name
		const countryCode = countries.find((c) => c.name === country).code;

		//Send get request to backend to get coordinates. Add headers to avoid CORS error
		fetch(
			`http://localhost:8080/geolocation?city=${city}&countryCode=${countryCode}`,
			{
				method: "GET",
				headers: {
					credentials: "include",
				},
			}
		)
			.then((response) => response.json())
			.then((data) => {
				console.log(data);
				// Send get request to backend to get air pollution
				if (data.length === 0) {
					console.log("No data found");
					return;
				}
				fetch(
					`http://localhost:8080/air-pollution?lat=${data[0].lat}&lon=${data[0].lon}`,
					{
						method: "GET",
						headers: {
							credentials: "include",
							user: "admin",
							password: "admin",
						},
					}
				)
					.then((response) => response.json())
					.then((data) => {
						console.log(data);
						setComponents(data.list[0].components);
					});
			});
	}

	const [country, setCountry] = React.useState("");
	const [city, setCity] = React.useState("");
	const [components, setComponents] = React.useState("");
	return (
		<Box display="flex" justifyContent="center">
			<Box
				display="flex"
				flexDirection="column"
				width="600px"
				textAlign="center"
			>
				<Box
				height="350px"
				display="flex"
				flexDirection="column"
				justifyContent="space-between"
				textAlign="center"
				>
				<h1>Air polution</h1>
				<Autocomplete
					value={country === "" ? null : country}
					id="country-autocomplete"
					options={countryList}
					onChange={(e, data) => setCountry(data)}
					renderInput={(params) => (
						<TextField
							{...params}
							label="Choose a country"
							variant="outlined"
						/>
					)}
				/>
				<TextField
					value={city}
					onChange={(e) => setCity(e.target.value)}
					label="Choose a city"
					variant="outlined"
					InputProps={{
						// if the country is not selected, disable the input
						disabled: !country,
					}}
				/>
				<Button
					sx={{
						visibility: country && city ? "visible" : "hidden",
					}}
					variant="contained"
					onClick={() => {
						getAirPollution(country, city);
					}}
				>
					Search
				</Button>
				</Box>
				<Box
			width="600px"
					margin="auto"
				>
				<Table sx={{
						visibility: components ? "visible" : "hidden",
					}}>
					<TableHead>
						<TableRow>
							<TableCell>Pollutant</TableCell>
							<TableCell>Value</TableCell>
						</TableRow>
					</TableHead>
					<TableBody>
						{Object.entries(components).map(
							([component, value]) => (
								<TableRow key={component} >
									<TableCell>{component}</TableCell>
									<TableCell>{value}</TableCell>
								</TableRow>
							)
						)}
					</TableBody>
				</Table>
			</Box>
			</Box>
			
		</Box>
	);
}

export default App;
