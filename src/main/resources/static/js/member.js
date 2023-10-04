async function isAvailableID(id)
{
    const result = await axios.get(`/memberRest/availableMemberID/${id}`)
    return result.data
}

async function isAvailableNickname(name)
{
    const result = await axios.get(`/memberRest/availableNickname/${name}`)
    return result.data
}

async function isAvailablePassword(password, passwordCheck)
{
    console.info(password + " vs " + passwordCheck)
    const result = await axios.get(`/memberRest/availablePassword/${password}/passwordCheck/${passwordCheck}`)
    return result.data
}