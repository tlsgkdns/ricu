async function isAvailableID(id)
{
    const result = await axios.get(`/memberRest/memberID/${id}`)
    return result.data
}

async function isAvailableNickname(name)
{
    const result = await axios.get(`/memberRest/nickname/${name}`)
    return result.data
}

async function isAvailablePassword(password, passwordCheck)
{
    console.info(password + " vs " + passwordCheck)
    const result = await axios.get(`/memberRest/password/${password}/${passwordCheck}`)
    return result.data
}